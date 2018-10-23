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
import static com.edevapps.util.AssertUtil.assertNotNullOrEmpty;

import com.edevapps.confluence.util.ComponentsUtil;

public class RequestContextParameterImpl implements RequestContextParameter {
	
	private final String baseUrl;
	
	public RequestContextParameterImpl(String baseUrl) {
		this.baseUrl = assertNotNullOrEmpty(baseUrl, "baseUrl");
	}
	
	public RequestContextParameterImpl() {
		this.baseUrl = assertNotNull(ComponentsUtil.getSettingsManager().getGlobalSettings(),
				"ComponentsUtil.getSettingsManager().getGlobalSettings()").getBaseUrl();
	}
	
	@Override
	public String getBaseUrl() {
		return this.baseUrl;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder implements com.edevapps.Builder<RequestContextParameterImpl> {
		
		private String baseUrl;
		
		public Builder setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}
		
		@Override
		public RequestContextParameterImpl build() {
			if(this.baseUrl != null) {
				return new RequestContextParameterImpl(this.baseUrl);
			}
			return new RequestContextParameterImpl();
		}
	}
}
