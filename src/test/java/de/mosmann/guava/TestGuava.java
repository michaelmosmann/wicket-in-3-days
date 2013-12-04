package de.mosmann.guava;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.concurrent.Immutable;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Lists;


public class TestGuava {

	private final class StringToInt implements Function<String, Integer> {

		@Override
		public Integer apply(String input) {
			return Integer.valueOf(input);
		}
	}
	
	public final class IntMul implements Function<Integer, Integer> {

		@Override
		public Integer apply(Integer input) {
			return input + input;
		}
		
	}

	@Test
	public void stringAsInt() {
		List<String> strings = Lists.newArrayList("1","5","17");
		Function<String, Integer> stringAsIntAddedBySelf = Functions.compose(new IntMul(), new StringToInt());
		List<Integer> ints = Lists.transform(strings, stringAsIntAddedBySelf);
		
		assertEquals(Lists.newArrayList(2,10,34),ints);
	}
	
	@Test
	public void stringsAsMap() {
		List<String> strings = Lists.newArrayList("Klaus","Bert","Danny");
		Map<String, String> map = map(strings,new Function<String, String>() {
			@Override
			public String apply(String input) {
				return input.substring(0,1);
			}
		});
		assertEquals("Klaus", map.get("K"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void wrongIndex() {
		List<String> strings = Lists.newArrayList("Klaus","Bert","Danny");
		subList(strings, -1,1);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void wrongIndex2() {
		List<String> strings = Lists.newArrayList("Klaus","Bert","Danny");
		subList(strings, new GreaterZeroInt(-1),new Count(1));
	}

	static <T> List<T> subList(List<T> source, int startidx, int len) {
		Preconditions.checkArgument(startidx>0,"value NOT > 0"); 
		return source.subList(startidx, startidx+len);
	}
	
	static <T> List<T> subList(List<T> source, GreaterZeroInt startidx, Count len) {
		return source.subList(startidx.value(), startidx.value()+len.value());
	}
	
	static class GreaterZeroInt {
		
		private final int _value;

		public GreaterZeroInt(int value) {
			Preconditions.checkArgument(value>0,"value NOT > 0"); 
			_value = value;
		}
		
		public int value() {
			return _value;
		}
	}

	static class Count {
		
		private final int _value;

		public Count(int value) {
			Preconditions.checkArgument(value>=0,"value NOT > 0"); 
			_value = value;
		}
		
		public int value() {
			return _value;
		}
	}
	
	static class Email {
		
		private final String _email;

		public Email(String email) {
			_email = Objects.requireNonNull(email,"email is null");
		}
		
		public String email() {
			return _email;
		}
	}
	
	
	static <K, V> Map<K, V> map(Collection<V> values, Function<V, K> keyTransformation) {
		return map(values,keyTransformation,new Noop<V>());
	}
	
	static <T, K, V> Map<K, V> map(Collection<T> values, Function<T, K> keyTransformation, Function<T, V> valueTransformation) {
		Builder<K, V> builder = ImmutableMap.builder();
		for (T value :values) {
			builder.put(keyTransformation.apply(value),valueTransformation.apply(value));
		}
		return builder.build();
	}

	static class Noop<X> implements Function<X, X> {

		@Override
		public X apply(X input) {
			return input;
		}
		
	}
}
