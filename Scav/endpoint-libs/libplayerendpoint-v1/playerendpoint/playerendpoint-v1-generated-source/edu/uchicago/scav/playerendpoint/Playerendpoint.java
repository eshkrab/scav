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
 * This file was generated.
 *  with google-apis-code-generator 1.2.0 (build: 2013-03-04 17:44:07 UTC)
 *  on 2013-03-09 at 19:13:54 UTC 
 */

package edu.uchicago.scav.playerendpoint;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.common.base.Preconditions;

/**
 * Service definition for Playerendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link PlayerendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * <p>
 * Upgrade warning: this class now extends {@link AbstractGoogleJsonClient}, whereas in prior
 * version 1.8 it extended {@link com.google.api.client.googleapis.services.GoogleClient}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Playerendpoint extends AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    Preconditions.checkState(GoogleUtils.VERSION.equals("1.13.2-beta"),
        "You are currently running with version %s of google-api-client. " +
        "You need version 1.13.2-beta of google-api-client to run version " +
        "1.13.2-beta of the  library.", GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://scavapp.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "playerendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   * @deprecated (scheduled to be removed in 1.13)
   */
  @Deprecated
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport
   * @param jsonFactory JSON factory
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Playerendpoint(HttpTransport transport, JsonFactory jsonFactory,
      HttpRequestInitializer httpRequestInitializer) {
    super(transport,
        jsonFactory,
        DEFAULT_ROOT_URL,
        DEFAULT_SERVICE_PATH,
        httpRequestInitializer,
        false);
  }

  /**
   * @param transport HTTP transport
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @param rootUrl root URL of the service
   * @param servicePath service path
   * @param jsonObjectParser JSON object parser
   * @param googleClientRequestInitializer Google request initializer or {@code null} for none
   * @param applicationName application name to be sent in the User-Agent header of requests or
   *        {@code null} for none
   * @param suppressPatternChecks whether discovery pattern checks should be suppressed on required
   *        parameters
   */
  Playerendpoint(HttpTransport transport,
      HttpRequestInitializer httpRequestInitializer,
      String rootUrl,
      String servicePath,
      JsonObjectParser jsonObjectParser,
      GoogleClientRequestInitializer googleClientRequestInitializer,
      String applicationName,
      boolean suppressPatternChecks) {
    super(transport,
        httpRequestInitializer,
        rootUrl,
        servicePath,
        jsonObjectParser,
        googleClientRequestInitializer,
        applicationName,
        suppressPatternChecks);
  }

  @Override
  protected void initialize(AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link GetPlayer#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetPlayer getPlayer(String id) throws java.io.IOException {
    GetPlayer result = new GetPlayer(id);
    initialize(result);
    return result;
  }

  public class GetPlayer extends PlayerendpointRequest<edu.uchicago.scav.playerendpoint.model.Player> {

    private static final String REST_PATH = "player/{id}";

    /**
     * Create a request for the method "getPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link GetPlayer#execute()} method to invoke the remote
     * operation. <p> {@link GetPlayer#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetPlayer(String id) {
      super(Playerendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.playerendpoint.model.Player.class);
      this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetPlayer setAlt(String alt) {
      return (GetPlayer) super.setAlt(alt);
    }

    @Override
    public GetPlayer setFields(String fields) {
      return (GetPlayer) super.setFields(fields);
    }

    @Override
    public GetPlayer setKey(String key) {
      return (GetPlayer) super.setKey(key);
    }

    @Override
    public GetPlayer setOauthToken(String oauthToken) {
      return (GetPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public GetPlayer setPrettyPrint(Boolean prettyPrint) {
      return (GetPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetPlayer setQuotaUser(String quotaUser) {
      return (GetPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetPlayer setUserIp(String userIp) {
      return (GetPlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String id;

    /**

     */
    public String getId() {
      return id;
    }

    public GetPlayer setId(String id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "insertPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link InsertPlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link edu.uchicago.scav.playerendpoint.model.Player}
   * @return the request
   */
  public InsertPlayer insertPlayer(edu.uchicago.scav.playerendpoint.model.Player content) throws java.io.IOException {
    InsertPlayer result = new InsertPlayer(content);
    initialize(result);
    return result;
  }

  public class InsertPlayer extends PlayerendpointRequest<edu.uchicago.scav.playerendpoint.model.Player> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "insertPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link InsertPlayer#execute()} method to invoke the remote
     * operation. <p> {@link InsertPlayer#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.playerendpoint.model.Player}
     * @since 1.13
     */
    protected InsertPlayer(edu.uchicago.scav.playerendpoint.model.Player content) {
      super(Playerendpoint.this, "POST", REST_PATH, content, edu.uchicago.scav.playerendpoint.model.Player.class);
    }

    @Override
    public InsertPlayer setAlt(String alt) {
      return (InsertPlayer) super.setAlt(alt);
    }

    @Override
    public InsertPlayer setFields(String fields) {
      return (InsertPlayer) super.setFields(fields);
    }

    @Override
    public InsertPlayer setKey(String key) {
      return (InsertPlayer) super.setKey(key);
    }

    @Override
    public InsertPlayer setOauthToken(String oauthToken) {
      return (InsertPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertPlayer setPrettyPrint(Boolean prettyPrint) {
      return (InsertPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertPlayer setQuotaUser(String quotaUser) {
      return (InsertPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertPlayer setUserIp(String userIp) {
      return (InsertPlayer) super.setUserIp(userIp);
    }

  }

  /**
   * Create a request for the method "listPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link ListPlayer#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListPlayer listPlayer() throws java.io.IOException {
    ListPlayer result = new ListPlayer();
    initialize(result);
    return result;
  }

  public class ListPlayer extends PlayerendpointRequest<edu.uchicago.scav.playerendpoint.model.CollectionResponsePlayer> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "listPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link ListPlayer#execute()} method to invoke the remote
     * operation. <p> {@link ListPlayer#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListPlayer() {
      super(Playerendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.playerendpoint.model.CollectionResponsePlayer.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListPlayer setAlt(String alt) {
      return (ListPlayer) super.setAlt(alt);
    }

    @Override
    public ListPlayer setFields(String fields) {
      return (ListPlayer) super.setFields(fields);
    }

    @Override
    public ListPlayer setKey(String key) {
      return (ListPlayer) super.setKey(key);
    }

    @Override
    public ListPlayer setOauthToken(String oauthToken) {
      return (ListPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public ListPlayer setPrettyPrint(Boolean prettyPrint) {
      return (ListPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListPlayer setQuotaUser(String quotaUser) {
      return (ListPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListPlayer setUserIp(String userIp) {
      return (ListPlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String cursor;

    /**

     */
    public String getCursor() {
      return cursor;
    }

    public ListPlayer setCursor(String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private Integer limit;

    /**

     */
    public Integer getLimit() {
      return limit;
    }

    public ListPlayer setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

  }

  /**
   * Create a request for the method "removePlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link RemovePlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemovePlayer removePlayer(String id) throws java.io.IOException {
    RemovePlayer result = new RemovePlayer(id);
    initialize(result);
    return result;
  }

  public class RemovePlayer extends PlayerendpointRequest<edu.uchicago.scav.playerendpoint.model.Player> {

    private static final String REST_PATH = "player/{id}";

    /**
     * Create a request for the method "removePlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link RemovePlayer#execute()} method to invoke the remote
     * operation. <p> {@link RemovePlayer#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemovePlayer(String id) {
      super(Playerendpoint.this, "DELETE", REST_PATH, null, edu.uchicago.scav.playerendpoint.model.Player.class);
      this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemovePlayer setAlt(String alt) {
      return (RemovePlayer) super.setAlt(alt);
    }

    @Override
    public RemovePlayer setFields(String fields) {
      return (RemovePlayer) super.setFields(fields);
    }

    @Override
    public RemovePlayer setKey(String key) {
      return (RemovePlayer) super.setKey(key);
    }

    @Override
    public RemovePlayer setOauthToken(String oauthToken) {
      return (RemovePlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public RemovePlayer setPrettyPrint(Boolean prettyPrint) {
      return (RemovePlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemovePlayer setQuotaUser(String quotaUser) {
      return (RemovePlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemovePlayer setUserIp(String userIp) {
      return (RemovePlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String id;

    /**

     */
    public String getId() {
      return id;
    }

    public RemovePlayer setId(String id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "updatePlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link UpdatePlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link edu.uchicago.scav.playerendpoint.model.Player}
   * @return the request
   */
  public UpdatePlayer updatePlayer(edu.uchicago.scav.playerendpoint.model.Player content) throws java.io.IOException {
    UpdatePlayer result = new UpdatePlayer(content);
    initialize(result);
    return result;
  }

  public class UpdatePlayer extends PlayerendpointRequest<edu.uchicago.scav.playerendpoint.model.Player> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "updatePlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link UpdatePlayer#execute()} method to invoke the remote
     * operation. <p> {@link UpdatePlayer#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.playerendpoint.model.Player}
     * @since 1.13
     */
    protected UpdatePlayer(edu.uchicago.scav.playerendpoint.model.Player content) {
      super(Playerendpoint.this, "PUT", REST_PATH, content, edu.uchicago.scav.playerendpoint.model.Player.class);
    }

    @Override
    public UpdatePlayer setAlt(String alt) {
      return (UpdatePlayer) super.setAlt(alt);
    }

    @Override
    public UpdatePlayer setFields(String fields) {
      return (UpdatePlayer) super.setFields(fields);
    }

    @Override
    public UpdatePlayer setKey(String key) {
      return (UpdatePlayer) super.setKey(key);
    }

    @Override
    public UpdatePlayer setOauthToken(String oauthToken) {
      return (UpdatePlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdatePlayer setPrettyPrint(Boolean prettyPrint) {
      return (UpdatePlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdatePlayer setQuotaUser(String quotaUser) {
      return (UpdatePlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdatePlayer setUserIp(String userIp) {
      return (UpdatePlayer) super.setUserIp(userIp);
    }

  }

  /**
   * Builder for {@link Playerendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport
     * @param jsonFactory JSON factory
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(HttpTransport transport, JsonFactory jsonFactory,
        HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Playerendpoint}. */
    @Override
    public Playerendpoint build() {
      return new Playerendpoint(getTransport(),
          getHttpRequestInitializer(),
          getRootUrl(),
          getServicePath(),
          getObjectParser(),
          getGoogleClientRequestInitializer(),
          getApplicationName(),
          getSuppressPatternChecks());
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    /**
     * Set the {@link PlayerendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setPlayerendpointRequestInitializer(
        PlayerendpointRequestInitializer playerendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(playerendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
