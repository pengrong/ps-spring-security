-- used in tests that use oracle
create table oauth_client_details (
  client_id VARCHAR2(256) PRIMARY KEY,
  resource_ids VARCHAR2(256),
  client_secret VARCHAR2(256),
  scope VARCHAR2(256),
  authorized_grant_types VARCHAR2(256),
  web_server_redirect_uri VARCHAR2(256),
  authorities VARCHAR2(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR2(4000),
  autoapprove VARCHAR2(256)
);

create table oauth_client_token (
  token_id VARCHAR2(256),
  token blob,
  authentication_id VARCHAR2(256) PRIMARY KEY,
  user_name VARCHAR2(256),
  client_id VARCHAR2(256)
);

create table oauth_access_token (
  token_id VARCHAR2(256),
  token blob,
  authentication_id VARCHAR2(256) PRIMARY KEY,
  user_name VARCHAR2(256),
  client_id VARCHAR2(256),
  authentication blob,
  refresh_token VARCHAR2(256)
);

create table oauth_refresh_token (
  token_id VARCHAR2(256),
  token blob,
  authentication blob
);

create table oauth_code (
  code VARCHAR2(256), authentication blob
);

create table oauth_approvals (
  userId VARCHAR2(256),
  clientId VARCHAR2(256),
  scope VARCHAR2(256),
  status VARCHAR2(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR2(256) PRIMARY KEY,
  resourceIds VARCHAR2(256),
  appSecret VARCHAR2(256),
  scope VARCHAR2(256),
  grantTypes VARCHAR2(256),
  redirectUrl VARCHAR2(256),
  authorities VARCHAR2(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR2(4000),
  autoApproveScopes VARCHAR2(256)
);