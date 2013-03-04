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
 *  with google-apis-code-generator 1.2.0 (build: 2013-02-14 15:45:00 UTC)
 *  on 2013-03-04 at 01:57:25 UTC 
 */

package edu.uchicago.scav.teamendpoint;

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
 * Service definition for Teamendpoint (v1).
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
 * This service uses {@link TeamendpointRequestInitializer} to initialize global parameters via its
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
public class Teamendpoint extends AbstractGoogleJsonClient {

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
  public static final String DEFAULT_SERVICE_PATH = "teamendpoint/v1/";

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
  public Teamendpoint(HttpTransport transport, JsonFactory jsonFactory,
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
  Teamendpoint(HttpTransport transport,
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
   * Create a request for the method "getTeam".
   *
   * This request holds the parameters needed by the the teamendpoint server.  After setting any
   * optional parameters, call the {@link GetTeam#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetTeam getTeam(String id) throws java.io.IOException {
    GetTeam result = new GetTeam(id);
    initialize(result);
    return result;
  }

  public class GetTeam extends TeamendpointRequest<edu.uchicago.scav.teamendpoint.model.Team> {

    private static final String REST_PATH = "team/{id}";

    /**
     * Create a request for the method "getTeam".
     *
     * This request holds the parameters needed by the the teamendpoint server.  After setting any
     * optional parameters, call the {@link GetTeam#execute()} method to invoke the remote operation.
     * <p> {@link GetTeam#initialize(AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetTeam(String id) {
      super(Teamendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.teamendpoint.model.Team.class);
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
    public GetTeam setAlt(String alt) {
      return (GetTeam) super.setAlt(alt);
    }

    @Override
    public GetTeam setFields(String fields) {
      return (GetTeam) super.setFields(fields);
    }

    @Override
    public GetTeam setKey(String key) {
      return (GetTeam) super.setKey(key);
    }

    @Override
    public GetTeam setOauthToken(String oauthToken) {
      return (GetTeam) super.setOauthToken(oauthToken);
    }

    @Override
    public GetTeam setPrettyPrint(Boolean prettyPrint) {
      return (GetTeam) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetTeam setQuotaUser(String quotaUser) {
      return (GetTeam) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetTeam setUserIp(String userIp) {
      return (GetTeam) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String id;

    /**

     */
    public String getId() {
      return id;
    }

    public GetTeam setId(String id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "insertTeam".
   *
   * This request holds the parameters needed by the the teamendpoint server.  After setting any
   * optional parameters, call the {@link InsertTeam#execute()} method to invoke the remote operation.
   *
   * @param content the {@link edu.uchicago.scav.teamendpoint.model.Team}
   * @return the request
   */
  public InsertTeam insertTeam(edu.uchicago.scav.teamendpoint.model.Team content) throws java.io.IOException {
    InsertTeam result = new InsertTeam(content);
    initialize(result);
    return result;
  }

  public class InsertTeam extends TeamendpointRequest<edu.uchicago.scav.teamendpoint.model.Team> {

    private static final String REST_PATH = "team";

    /**
     * Create a request for the method "insertTeam".
     *
     * This request holds the parameters needed by the the teamendpoint server.  After setting any
     * optional parameters, call the {@link InsertTeam#execute()} method to invoke the remote
     * operation. <p> {@link InsertTeam#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.teamendpoint.model.Team}
     * @since 1.13
     */
    protected InsertTeam(edu.uchicago.scav.teamendpoint.model.Team content) {
      super(Teamendpoint.this, "POST", REST_PATH, content, edu.uchicago.scav.teamendpoint.model.Team.class);
    }

    @Override
    public InsertTeam setAlt(String alt) {
      return (InsertTeam) super.setAlt(alt);
    }

    @Override
    public InsertTeam setFields(String fields) {
      return (InsertTeam) super.setFields(fields);
    }

    @Override
    public InsertTeam setKey(String key) {
      return (InsertTeam) super.setKey(key);
    }

    @Override
    public InsertTeam setOauthToken(String oauthToken) {
      return (InsertTeam) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertTeam setPrettyPrint(Boolean prettyPrint) {
      return (InsertTeam) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertTeam setQuotaUser(String quotaUser) {
      return (InsertTeam) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertTeam setUserIp(String userIp) {
      return (InsertTeam) super.setUserIp(userIp);
    }

  }

  /**
   * Create a request for the method "listTeam".
   *
   * This request holds the parameters needed by the the teamendpoint server.  After setting any
   * optional parameters, call the {@link ListTeam#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListTeam listTeam() throws java.io.IOException {
    ListTeam result = new ListTeam();
    initialize(result);
    return result;
  }

  public class ListTeam extends TeamendpointRequest<edu.uchicago.scav.teamendpoint.model.CollectionResponseTeam> {

    private static final String REST_PATH = "team";

    /**
     * Create a request for the method "listTeam".
     *
     * This request holds the parameters needed by the the teamendpoint server.  After setting any
     * optional parameters, call the {@link ListTeam#execute()} method to invoke the remote operation.
     * <p> {@link ListTeam#initialize(AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListTeam() {
      super(Teamendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.teamendpoint.model.CollectionResponseTeam.class);
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
    public ListTeam setAlt(String alt) {
      return (ListTeam) super.setAlt(alt);
    }

    @Override
    public ListTeam setFields(String fields) {
      return (ListTeam) super.setFields(fields);
    }

    @Override
    public ListTeam setKey(String key) {
      return (ListTeam) super.setKey(key);
    }

    @Override
    public ListTeam setOauthToken(String oauthToken) {
      return (ListTeam) super.setOauthToken(oauthToken);
    }

    @Override
    public ListTeam setPrettyPrint(Boolean prettyPrint) {
      return (ListTeam) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListTeam setQuotaUser(String quotaUser) {
      return (ListTeam) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListTeam setUserIp(String userIp) {
      return (ListTeam) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String cursor;

    /**

     */
    public String getCursor() {
      return cursor;
    }

    public ListTeam setCursor(String cursor) {
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

    public ListTeam setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

  }

  /**
   * Create a request for the method "removeTeam".
   *
   * This request holds the parameters needed by the the teamendpoint server.  After setting any
   * optional parameters, call the {@link RemoveTeam#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveTeam removeTeam(String id) throws java.io.IOException {
    RemoveTeam result = new RemoveTeam(id);
    initialize(result);
    return result;
  }

  public class RemoveTeam extends TeamendpointRequest<edu.uchicago.scav.teamendpoint.model.Team> {

    private static final String REST_PATH = "team/{id}";

    /**
     * Create a request for the method "removeTeam".
     *
     * This request holds the parameters needed by the the teamendpoint server.  After setting any
     * optional parameters, call the {@link RemoveTeam#execute()} method to invoke the remote
     * operation. <p> {@link RemoveTeam#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveTeam(String id) {
      super(Teamendpoint.this, "DELETE", REST_PATH, null, edu.uchicago.scav.teamendpoint.model.Team.class);
      this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveTeam setAlt(String alt) {
      return (RemoveTeam) super.setAlt(alt);
    }

    @Override
    public RemoveTeam setFields(String fields) {
      return (RemoveTeam) super.setFields(fields);
    }

    @Override
    public RemoveTeam setKey(String key) {
      return (RemoveTeam) super.setKey(key);
    }

    @Override
    public RemoveTeam setOauthToken(String oauthToken) {
      return (RemoveTeam) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveTeam setPrettyPrint(Boolean prettyPrint) {
      return (RemoveTeam) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveTeam setQuotaUser(String quotaUser) {
      return (RemoveTeam) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveTeam setUserIp(String userIp) {
      return (RemoveTeam) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String id;

    /**

     */
    public String getId() {
      return id;
    }

    public RemoveTeam setId(String id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "updateTeam".
   *
   * This request holds the parameters needed by the the teamendpoint server.  After setting any
   * optional parameters, call the {@link UpdateTeam#execute()} method to invoke the remote operation.
   *
   * @param content the {@link edu.uchicago.scav.teamendpoint.model.Team}
   * @return the request
   */
  public UpdateTeam updateTeam(edu.uchicago.scav.teamendpoint.model.Team content) throws java.io.IOException {
    UpdateTeam result = new UpdateTeam(content);
    initialize(result);
    return result;
  }

  public class UpdateTeam extends TeamendpointRequest<edu.uchicago.scav.teamendpoint.model.Team> {

    private static final String REST_PATH = "team";

    /**
     * Create a request for the method "updateTeam".
     *
     * This request holds the parameters needed by the the teamendpoint server.  After setting any
     * optional parameters, call the {@link UpdateTeam#execute()} method to invoke the remote
     * operation. <p> {@link UpdateTeam#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.teamendpoint.model.Team}
     * @since 1.13
     */
    protected UpdateTeam(edu.uchicago.scav.teamendpoint.model.Team content) {
      super(Teamendpoint.this, "PUT", REST_PATH, content, edu.uchicago.scav.teamendpoint.model.Team.class);
    }

    @Override
    public UpdateTeam setAlt(String alt) {
      return (UpdateTeam) super.setAlt(alt);
    }

    @Override
    public UpdateTeam setFields(String fields) {
      return (UpdateTeam) super.setFields(fields);
    }

    @Override
    public UpdateTeam setKey(String key) {
      return (UpdateTeam) super.setKey(key);
    }

    @Override
    public UpdateTeam setOauthToken(String oauthToken) {
      return (UpdateTeam) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateTeam setPrettyPrint(Boolean prettyPrint) {
      return (UpdateTeam) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateTeam setQuotaUser(String quotaUser) {
      return (UpdateTeam) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateTeam setUserIp(String userIp) {
      return (UpdateTeam) super.setUserIp(userIp);
    }

  }

  /**
   * Builder for {@link Teamendpoint}.
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

    /** Builds a new instance of {@link Teamendpoint}. */
    @Override
    public Teamendpoint build() {
      return new Teamendpoint(getTransport(),
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
     * Set the {@link TeamendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setTeamendpointRequestInitializer(
        TeamendpointRequestInitializer teamendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(teamendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
