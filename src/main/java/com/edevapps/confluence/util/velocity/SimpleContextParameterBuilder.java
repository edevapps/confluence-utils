/*
 *     Copyright (c) 2018, The Eduard Burenkov (http://edevapps.com)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.edevapps.confluence.util.velocity;

import static com.edevapps.util.AssertUtil.assertNotNull;

import com.edevapps.Builder;

public class SimpleContextParameterBuilder<T> implements Builder<SimpleContextParameter<T>> {
	
	private String key;
	private T value;
	
	public SimpleContextParameterBuilder<T> setKey(String key) {
		this.key = key;
		return this;
	}
	
	public SimpleContextParameterBuilder<T> setValue(T value) {
		this.value = value;
		return this;
	}
	
	@Override
	public void validate() throws IllegalStateException {
		assertNotNull(this.key, "key");
	}
	
	private static class SimpleContextParameterImpl<T> implements SimpleContextParameter<T> {
		
		private final String key;
		private T value;
		
		public SimpleContextParameterImpl(String key, T value) {
			this.key = assertNotNull(key, "key");
			this.value = value;
		}
		
		@Override
		public T getValue() {
			return this.value;
		}
		
		@Override
		public String getKey() {
			return this.key;
		}
	}
	
	@Override
	public SimpleContextParameter<T> build() {
		validate();
		return new SimpleContextParameterImpl<>(this.key, this.value);
	}
}
