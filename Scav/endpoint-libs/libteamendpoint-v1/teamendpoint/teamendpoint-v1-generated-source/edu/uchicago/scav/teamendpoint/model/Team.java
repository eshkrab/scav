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

package edu.uchicago.scav.teamendpoint.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;

/**
 * Model definition for Team.
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
public final class Team extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Player captain;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @JsonString
  private Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Player> members;

  static {
    // hack to force ProGuard to consider Player used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(Player.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String name;

  /**

   * The value returned may be {@code null}.
   */
  public Player getCaptain() {
    return captain;
  }

  /**

   * The value set may be {@code null}.
   */
  public Team setCaptain(Player captain) {
    this.captain = captain;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getDescription() {
    return description;
  }

  /**

   * The value set may be {@code null}.
   */
  public Team setDescription(String description) {
    this.description = description;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public Long getId() {
    return id;
  }

  /**

   * The value set may be {@code null}.
   */
  public Team setId(Long id) {
    this.id = id;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<Player> getMembers() {
    return members;
  }

  /**

   * The value set may be {@code null}.
   */
  public Team setMembers(java.util.List<Player> members) {
    this.members = members;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getName() {
    return name;
  }

  /**

   * The value set may be {@code null}.
   */
  public Team setName(String name) {
    this.name = name;
    return this;
  }

}
