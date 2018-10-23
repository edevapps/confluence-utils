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

package com.edevapps.confluence.util;


import com.atlassian.confluence.core.ContentPermissionManager;
import com.atlassian.confluence.languages.LocaleManager;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.spring.container.ContainerManager;

public class ComponentsUtil {
  
  private static final String CONTENT_PERMISSION_MANAGER_COMP = "contentPermissionManager";
  private static final String PAGE_MANAGER_COMP = "pageManager";
  private static final String SETTINGS_MANAGER = "settingsManager";
  private static final String LOCALE_MANAGER = "localeManager";
  
  public static ContentPermissionManager getContentPermissionManager() {
    return ContainerManager
        .getComponent(CONTENT_PERMISSION_MANAGER_COMP, ContentPermissionManager.class);
  }
  
  public static PageManager getPageManager() {
    return ContainerManager.getComponent(PAGE_MANAGER_COMP, PageManager.class);
  }
  
  public static SettingsManager getSettingsManager() {
    return ContainerManager.getComponent(SETTINGS_MANAGER, SettingsManager.class);
  }
  
  public static LocaleManager getLocaleManager() {
    return ContainerManager.getComponent(LOCALE_MANAGER, LocaleManager.class);
  }
}
