/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * Warning! This file is generated. Modify at your own risk.
 */

package edu.uchicago.scav.deviceinfoendpoint.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;

/**
 * Model definition for DeviceInfo.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * <p>
 * Upgrade warning: starting with version 1.12 {@code getResponseHeaders()} is removed, instead use
 * {@link com.google.api.client.http.json.JsonHttpRequest#getLastResponseHeaders()}
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class DeviceInfo extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String deviceInformation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String deviceRegistrationID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @JsonString
  private Long timestamp;

  /**

   * The value returned may be {@code null}.
   */
  public String getDeviceInformation() {
    return deviceInformation;
  }

  /**

   * The value set may be {@code null}.
   */
  public DeviceInfo setDeviceInformation(String deviceInformation) {
    this.deviceInformation = deviceInformation;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getDeviceRegistrationID() {
    return deviceRegistrationID;
  }

  /**

   * The value set may be {@code null}.
   */
  public DeviceInfo setDeviceRegistrationID(String deviceRegistrationID) {
    this.deviceRegistrationID = deviceRegistrationID;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**

   * The value set may be {@code null}.
   */
  public DeviceInfo setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

}
