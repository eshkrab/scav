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
 *  on 2013-03-11 at 18:10:38 UTC 
 */

package edu.uchicago.scav.itemendpoint;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonString;
import com.google.common.base.Preconditions;

/**
 * Service definition for Itemendpoint (v1).
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
 * This service uses {@link ItemendpointRequestInitializer} to initialize global parameters via its
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
public class Itemendpoint extends AbstractGoogleJsonClient {

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
  public static final String DEFAULT_SERVICE_PATH = "itemendpoint/v1/";

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
  public Itemendpoint(HttpTransport transport, JsonFactory jsonFactory,
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
  Itemendpoint(HttpTransport transport,
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
   * Create a request for the method "getItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link GetItem#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetItem getItem(Long id) throws java.io.IOException {
    GetItem result = new GetItem(id);
    initialize(result);
    return result;
  }

  public class GetItem extends ItemendpointRequest<edu.uchicago.scav.itemendpoint.model.Item> {

    private static final String REST_PATH = "item/{id}";

    /**
     * Create a request for the method "getItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link GetItem#execute()} method to invoke the remote operation.
     * <p> {@link GetItem#initialize(AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetItem(Long id) {
      super(Itemendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.itemendpoint.model.Item.class);
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
    public GetItem setAlt(String alt) {
      return (GetItem) super.setAlt(alt);
    }

    @Override
    public GetItem setFields(String fields) {
      return (GetItem) super.setFields(fields);
    }

    @Override
    public GetItem setKey(String key) {
      return (GetItem) super.setKey(key);
    }

    @Override
    public GetItem setOauthToken(String oauthToken) {
      return (GetItem) super.setOauthToken(oauthToken);
    }

    @Override
    public GetItem setPrettyPrint(Boolean prettyPrint) {
      return (GetItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetItem setQuotaUser(String quotaUser) {
      return (GetItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetItem setUserIp(String userIp) {
      return (GetItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private Long id;

    /**

     */
    public Long getId() {
      return id;
    }

    public GetItem setId(Long id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "insertItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link InsertItem#execute()} method to invoke the remote operation.
   *
   * @param content the {@link edu.uchicago.scav.itemendpoint.model.Item}
   * @return the request
   */
  public InsertItem insertItem(edu.uchicago.scav.itemendpoint.model.Item content) throws java.io.IOException {
    InsertItem result = new InsertItem(content);
    initialize(result);
    return result;
  }

  public class InsertItem extends ItemendpointRequest<edu.uchicago.scav.itemendpoint.model.Item> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "insertItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link InsertItem#execute()} method to invoke the remote
     * operation. <p> {@link InsertItem#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.itemendpoint.model.Item}
     * @since 1.13
     */
    protected InsertItem(edu.uchicago.scav.itemendpoint.model.Item content) {
      super(Itemendpoint.this, "POST", REST_PATH, content, edu.uchicago.scav.itemendpoint.model.Item.class);
    }

    @Override
    public InsertItem setAlt(String alt) {
      return (InsertItem) super.setAlt(alt);
    }

    @Override
    public InsertItem setFields(String fields) {
      return (InsertItem) super.setFields(fields);
    }

    @Override
    public InsertItem setKey(String key) {
      return (InsertItem) super.setKey(key);
    }

    @Override
    public InsertItem setOauthToken(String oauthToken) {
      return (InsertItem) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertItem setPrettyPrint(Boolean prettyPrint) {
      return (InsertItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertItem setQuotaUser(String quotaUser) {
      return (InsertItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertItem setUserIp(String userIp) {
      return (InsertItem) super.setUserIp(userIp);
    }

  }

  /**
   * Create a request for the method "listItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link ListItem#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListItem listItem() throws java.io.IOException {
    ListItem result = new ListItem();
    initialize(result);
    return result;
  }

  public class ListItem extends ItemendpointRequest<edu.uchicago.scav.itemendpoint.model.CollectionResponseItem> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "listItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link ListItem#execute()} method to invoke the remote operation.
     * <p> {@link ListItem#initialize(AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListItem() {
      super(Itemendpoint.this, "GET", REST_PATH, null, edu.uchicago.scav.itemendpoint.model.CollectionResponseItem.class);
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
    public ListItem setAlt(String alt) {
      return (ListItem) super.setAlt(alt);
    }

    @Override
    public ListItem setFields(String fields) {
      return (ListItem) super.setFields(fields);
    }

    @Override
    public ListItem setKey(String key) {
      return (ListItem) super.setKey(key);
    }

    @Override
    public ListItem setOauthToken(String oauthToken) {
      return (ListItem) super.setOauthToken(oauthToken);
    }

    @Override
    public ListItem setPrettyPrint(Boolean prettyPrint) {
      return (ListItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListItem setQuotaUser(String quotaUser) {
      return (ListItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListItem setUserIp(String userIp) {
      return (ListItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private String cursor;

    /**

     */
    public String getCursor() {
      return cursor;
    }

    public ListItem setCursor(String cursor) {
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

    public ListItem setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

  }

  /**
   * Create a request for the method "removeItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link RemoveItem#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveItem removeItem(Long id) throws java.io.IOException {
    RemoveItem result = new RemoveItem(id);
    initialize(result);
    return result;
  }

  public class RemoveItem extends ItemendpointRequest<edu.uchicago.scav.itemendpoint.model.Item> {

    private static final String REST_PATH = "item/{id}";

    /**
     * Create a request for the method "removeItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link RemoveItem#execute()} method to invoke the remote
     * operation. <p> {@link RemoveItem#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveItem(Long id) {
      super(Itemendpoint.this, "DELETE", REST_PATH, null, edu.uchicago.scav.itemendpoint.model.Item.class);
      this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveItem setAlt(String alt) {
      return (RemoveItem) super.setAlt(alt);
    }

    @Override
    public RemoveItem setFields(String fields) {
      return (RemoveItem) super.setFields(fields);
    }

    @Override
    public RemoveItem setKey(String key) {
      return (RemoveItem) super.setKey(key);
    }

    @Override
    public RemoveItem setOauthToken(String oauthToken) {
      return (RemoveItem) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveItem setPrettyPrint(Boolean prettyPrint) {
      return (RemoveItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveItem setQuotaUser(String quotaUser) {
      return (RemoveItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveItem setUserIp(String userIp) {
      return (RemoveItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private Long id;

    /**

     */
    public Long getId() {
      return id;
    }

    public RemoveItem setId(Long id) {
      this.id = id;
      return this;
    }

  }

  /**
   * Create a request for the method "updateItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link UpdateItem#execute()} method to invoke the remote operation.
   *
   * @param content the {@link edu.uchicago.scav.itemendpoint.model.Item}
   * @return the request
   */
  public UpdateItem updateItem(edu.uchicago.scav.itemendpoint.model.Item content) throws java.io.IOException {
    UpdateItem result = new UpdateItem(content);
    initialize(result);
    return result;
  }

  public class UpdateItem extends ItemendpointRequest<edu.uchicago.scav.itemendpoint.model.Item> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "updateItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link UpdateItem#execute()} method to invoke the remote
     * operation. <p> {@link UpdateItem#initialize(AbstractGoogleClientRequest)} must be called to
     * initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link edu.uchicago.scav.itemendpoint.model.Item}
     * @since 1.13
     */
    protected UpdateItem(edu.uchicago.scav.itemendpoint.model.Item content) {
      super(Itemendpoint.this, "PUT", REST_PATH, content, edu.uchicago.scav.itemendpoint.model.Item.class);
    }

    @Override
    public UpdateItem setAlt(String alt) {
      return (UpdateItem) super.setAlt(alt);
    }

    @Override
    public UpdateItem setFields(String fields) {
      return (UpdateItem) super.setFields(fields);
    }

    @Override
    public UpdateItem setKey(String key) {
      return (UpdateItem) super.setKey(key);
    }

    @Override
    public UpdateItem setOauthToken(String oauthToken) {
      return (UpdateItem) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateItem setPrettyPrint(Boolean prettyPrint) {
      return (UpdateItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateItem setQuotaUser(String quotaUser) {
      return (UpdateItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateItem setUserIp(String userIp) {
      return (UpdateItem) super.setUserIp(userIp);
    }

  }

  /**
   * Builder for {@link Itemendpoint}.
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

    /** Builds a new instance of {@link Itemendpoint}. */
    @Override
    public Itemendpoint build() {
      return new Itemendpoint(getTransport(),
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
     * Set the {@link ItemendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setItemendpointRequestInitializer(
        ItemendpointRequestInitializer itemendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(itemendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
