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

package edu.uchicago.scav.playerendpoint.model;

import com.google.api.client.json.GenericJson;

/**
 * Model definition for Player.
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
public final class Player extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String about;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Email email;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private PhoneNumber number;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String pswd;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String team;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String userID;

  /**

   * The value returned may be {@code null}.
   */
  public String getAbout() {
    return about;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setAbout(String about) {
    this.about = about;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public Email getEmail() {
    return email;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setEmail(Email email) {
    this.email = email;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getId() {
    return id;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setId(String id) {
    this.id = id;
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
  public Player setName(String name) {
    this.name = name;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public PhoneNumber getNumber() {
    return number;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setNumber(PhoneNumber number) {
    this.number = number;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getPswd() {
    return pswd;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setPswd(String pswd) {
    this.pswd = pswd;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getTeam() {
    return team;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setTeam(String team) {
    this.team = team;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getUserID() {
    return userID;
  }

  /**

   * The value set may be {@code null}.
   */
  public Player setUserID(String userID) {
    this.userID = userID;
    return this;
  }

}
