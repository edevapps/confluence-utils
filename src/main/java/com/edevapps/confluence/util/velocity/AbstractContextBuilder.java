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

import com.atlassian.confluence.plugin.webresource.ConfluenceWebResourceManager;
import com.edevapps.Builder;
import com.edevapps.Factory;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractContextBuilder<T extends AbstractContextBuilder> implements
		Builder<Map<?, ?>> {
	
	protected static final String WEB_RESOURCE_MANAGER_PAR = "webResourceManager";
	
	private final List<ContextParameter> parameters = Lists.newArrayList();
	private ConfluenceWebResourceManager webResourceManager;
	
	protected AbstractContextBuilder() { }
	
	protected AbstractContextBuilder(ConfluenceWebResourceManager webResourceManager) {
		this.webResourceManager = webResourceManager;
	}
	
	@SuppressWarnings("unchecked")
	public T setWebResourceManager(ConfluenceWebResourceManager webResourceManager) {
		this.webResourceManager = webResourceManager;
		return (T)this;
	}
	
	public ConfluenceWebResourceManager getWebResourceManager() {
		return webResourceManager;
	}
	
	protected  List<ContextParameter> getParameters() {
		return this.parameters;
	}
	
	@SuppressWarnings("unchecked")
	public T addParameter(ContextParameter parameter) {
		this.parameters.add(parameter);
		return (T)this;
	}
	
	public static <T2 extends AbstractContextBuilder> T2 builder(Class<Factory<T2>> factory) {
		try {
			return assertNotNull(factory, "factory").newInstance().create();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
	
	protected Map createMap() {
		return new HashMap<>();
	}
	
	protected void onBuild(Map<?, ?> context) { }
	
	protected Object getParameterValue(ContextParameter parameter) {
		if(parameter instanceof SimpleContextParameter) {
			return ((SimpleContextParameter)parameter).getValue();
		}
		return parameter;
	}
	
	public Map<?, ?> build() {
		validate();
		Map context = createMap();
		
		if(this.webResourceManager != null) {
			context.put(WEB_RESOURCE_MANAGER_PAR, this.webResourceManager);
		}

		onBuild(context);
		
		this.parameters.forEach(parameter -> {
			context.put(parameter.getKey(), getParameterValue(parameter));
		});
		return context;
	}
}
