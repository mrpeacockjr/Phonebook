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
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-08-29 at 15:08:50 UTC 
 * Modify at your own risk.
 */

package com.example.phonebook.contactcopyendpoint;

/**
 * Service definition for Contactcopyendpoint (v1).
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
 * This service uses {@link ContactcopyendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Contactcopyendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the contactcopyendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://phonebookexample.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "contactcopyendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Contactcopyendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Contactcopyendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getContactCopy".
   *
   * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
   * any optional parameters, call the {@link GetContactCopy#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetContactCopy getContactCopy(java.lang.Long id) throws java.io.IOException {
    GetContactCopy result = new GetContactCopy(id);
    initialize(result);
    return result;
  }

  public class GetContactCopy extends ContactcopyendpointRequest<com.example.phonebook.contactcopyendpoint.model.ContactCopy> {

    private static final String REST_PATH = "contactcopy/{id}";

    /**
     * Create a request for the method "getContactCopy".
     *
     * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
     * any optional parameters, call the {@link GetContactCopy#execute()} method to invoke the remote
     * operation. <p> {@link GetContactCopy#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetContactCopy(java.lang.Long id) {
      super(Contactcopyendpoint.this, "GET", REST_PATH, null, com.example.phonebook.contactcopyendpoint.model.ContactCopy.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
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
    public GetContactCopy setAlt(java.lang.String alt) {
      return (GetContactCopy) super.setAlt(alt);
    }

    @Override
    public GetContactCopy setFields(java.lang.String fields) {
      return (GetContactCopy) super.setFields(fields);
    }

    @Override
    public GetContactCopy setKey(java.lang.String key) {
      return (GetContactCopy) super.setKey(key);
    }

    @Override
    public GetContactCopy setOauthToken(java.lang.String oauthToken) {
      return (GetContactCopy) super.setOauthToken(oauthToken);
    }

    @Override
    public GetContactCopy setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetContactCopy) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetContactCopy setQuotaUser(java.lang.String quotaUser) {
      return (GetContactCopy) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetContactCopy setUserIp(java.lang.String userIp) {
      return (GetContactCopy) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetContactCopy setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetContactCopy set(String parameterName, Object value) {
      return (GetContactCopy) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertContactCopy".
   *
   * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
   * any optional parameters, call the {@link InsertContactCopy#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.phonebook.contactcopyendpoint.model.ContactCopy}
   * @return the request
   */
  public InsertContactCopy insertContactCopy(com.example.phonebook.contactcopyendpoint.model.ContactCopy content) throws java.io.IOException {
    InsertContactCopy result = new InsertContactCopy(content);
    initialize(result);
    return result;
  }

  public class InsertContactCopy extends ContactcopyendpointRequest<com.example.phonebook.contactcopyendpoint.model.ContactCopy> {

    private static final String REST_PATH = "contactcopy";

    /**
     * Create a request for the method "insertContactCopy".
     *
     * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
     * any optional parameters, call the {@link InsertContactCopy#execute()} method to invoke the
     * remote operation. <p> {@link InsertContactCopy#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.example.phonebook.contactcopyendpoint.model.ContactCopy}
     * @since 1.13
     */
    protected InsertContactCopy(com.example.phonebook.contactcopyendpoint.model.ContactCopy content) {
      super(Contactcopyendpoint.this, "POST", REST_PATH, content, com.example.phonebook.contactcopyendpoint.model.ContactCopy.class);
    }

    @Override
    public InsertContactCopy setAlt(java.lang.String alt) {
      return (InsertContactCopy) super.setAlt(alt);
    }

    @Override
    public InsertContactCopy setFields(java.lang.String fields) {
      return (InsertContactCopy) super.setFields(fields);
    }

    @Override
    public InsertContactCopy setKey(java.lang.String key) {
      return (InsertContactCopy) super.setKey(key);
    }

    @Override
    public InsertContactCopy setOauthToken(java.lang.String oauthToken) {
      return (InsertContactCopy) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertContactCopy setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertContactCopy) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertContactCopy setQuotaUser(java.lang.String quotaUser) {
      return (InsertContactCopy) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertContactCopy setUserIp(java.lang.String userIp) {
      return (InsertContactCopy) super.setUserIp(userIp);
    }

    @Override
    public InsertContactCopy set(String parameterName, Object value) {
      return (InsertContactCopy) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listContactCopy".
   *
   * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
   * any optional parameters, call the {@link ListContactCopy#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListContactCopy listContactCopy() throws java.io.IOException {
    ListContactCopy result = new ListContactCopy();
    initialize(result);
    return result;
  }

  public class ListContactCopy extends ContactcopyendpointRequest<com.example.phonebook.contactcopyendpoint.model.CollectionResponseContactCopy> {

    private static final String REST_PATH = "contactcopy";

    /**
     * Create a request for the method "listContactCopy".
     *
     * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
     * any optional parameters, call the {@link ListContactCopy#execute()} method to invoke the remote
     * operation. <p> {@link ListContactCopy#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected ListContactCopy() {
      super(Contactcopyendpoint.this, "GET", REST_PATH, null, com.example.phonebook.contactcopyendpoint.model.CollectionResponseContactCopy.class);
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
    public ListContactCopy setAlt(java.lang.String alt) {
      return (ListContactCopy) super.setAlt(alt);
    }

    @Override
    public ListContactCopy setFields(java.lang.String fields) {
      return (ListContactCopy) super.setFields(fields);
    }

    @Override
    public ListContactCopy setKey(java.lang.String key) {
      return (ListContactCopy) super.setKey(key);
    }

    @Override
    public ListContactCopy setOauthToken(java.lang.String oauthToken) {
      return (ListContactCopy) super.setOauthToken(oauthToken);
    }

    @Override
    public ListContactCopy setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListContactCopy) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListContactCopy setQuotaUser(java.lang.String quotaUser) {
      return (ListContactCopy) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListContactCopy setUserIp(java.lang.String userIp) {
      return (ListContactCopy) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListContactCopy setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListContactCopy setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListContactCopy set(String parameterName, Object value) {
      return (ListContactCopy) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeContactCopy".
   *
   * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
   * any optional parameters, call the {@link RemoveContactCopy#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveContactCopy removeContactCopy(java.lang.Long id) throws java.io.IOException {
    RemoveContactCopy result = new RemoveContactCopy(id);
    initialize(result);
    return result;
  }

  public class RemoveContactCopy extends ContactcopyendpointRequest<Void> {

    private static final String REST_PATH = "contactcopy/{id}";

    /**
     * Create a request for the method "removeContactCopy".
     *
     * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
     * any optional parameters, call the {@link RemoveContactCopy#execute()} method to invoke the
     * remote operation. <p> {@link RemoveContactCopy#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveContactCopy(java.lang.Long id) {
      super(Contactcopyendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveContactCopy setAlt(java.lang.String alt) {
      return (RemoveContactCopy) super.setAlt(alt);
    }

    @Override
    public RemoveContactCopy setFields(java.lang.String fields) {
      return (RemoveContactCopy) super.setFields(fields);
    }

    @Override
    public RemoveContactCopy setKey(java.lang.String key) {
      return (RemoveContactCopy) super.setKey(key);
    }

    @Override
    public RemoveContactCopy setOauthToken(java.lang.String oauthToken) {
      return (RemoveContactCopy) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveContactCopy setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveContactCopy) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveContactCopy setQuotaUser(java.lang.String quotaUser) {
      return (RemoveContactCopy) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveContactCopy setUserIp(java.lang.String userIp) {
      return (RemoveContactCopy) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveContactCopy setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveContactCopy set(String parameterName, Object value) {
      return (RemoveContactCopy) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateContactCopy".
   *
   * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
   * any optional parameters, call the {@link UpdateContactCopy#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.phonebook.contactcopyendpoint.model.ContactCopy}
   * @return the request
   */
  public UpdateContactCopy updateContactCopy(com.example.phonebook.contactcopyendpoint.model.ContactCopy content) throws java.io.IOException {
    UpdateContactCopy result = new UpdateContactCopy(content);
    initialize(result);
    return result;
  }

  public class UpdateContactCopy extends ContactcopyendpointRequest<com.example.phonebook.contactcopyendpoint.model.ContactCopy> {

    private static final String REST_PATH = "contactcopy";

    /**
     * Create a request for the method "updateContactCopy".
     *
     * This request holds the parameters needed by the the contactcopyendpoint server.  After setting
     * any optional parameters, call the {@link UpdateContactCopy#execute()} method to invoke the
     * remote operation. <p> {@link UpdateContactCopy#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.example.phonebook.contactcopyendpoint.model.ContactCopy}
     * @since 1.13
     */
    protected UpdateContactCopy(com.example.phonebook.contactcopyendpoint.model.ContactCopy content) {
      super(Contactcopyendpoint.this, "PUT", REST_PATH, content, com.example.phonebook.contactcopyendpoint.model.ContactCopy.class);
    }

    @Override
    public UpdateContactCopy setAlt(java.lang.String alt) {
      return (UpdateContactCopy) super.setAlt(alt);
    }

    @Override
    public UpdateContactCopy setFields(java.lang.String fields) {
      return (UpdateContactCopy) super.setFields(fields);
    }

    @Override
    public UpdateContactCopy setKey(java.lang.String key) {
      return (UpdateContactCopy) super.setKey(key);
    }

    @Override
    public UpdateContactCopy setOauthToken(java.lang.String oauthToken) {
      return (UpdateContactCopy) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateContactCopy setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateContactCopy) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateContactCopy setQuotaUser(java.lang.String quotaUser) {
      return (UpdateContactCopy) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateContactCopy setUserIp(java.lang.String userIp) {
      return (UpdateContactCopy) super.setUserIp(userIp);
    }

    @Override
    public UpdateContactCopy set(String parameterName, Object value) {
      return (UpdateContactCopy) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Contactcopyendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Contactcopyendpoint}. */
    @Override
    public Contactcopyendpoint build() {
      return new Contactcopyendpoint(this);
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
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
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

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ContactcopyendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setContactcopyendpointRequestInitializer(
        ContactcopyendpointRequestInitializer contactcopyendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(contactcopyendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
