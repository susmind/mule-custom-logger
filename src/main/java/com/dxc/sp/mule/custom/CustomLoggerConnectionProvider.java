package com.dxc.sp.mule.custom;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;


/**
 * This class (as it's name implies) provides connection instances and the functionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class CustomLoggerConnectionProvider implements PoolingConnectionProvider<CustomLoggerConnection> {

  @Override
  public CustomLoggerConnection connect() throws ConnectionException {
    return new CustomLoggerConnection();
  }

  @Override
  public void disconnect(CustomLoggerConnection connection) {
    try {
      connection.invalidate();
    } catch (Exception e) {
       e.getMessage();
    }
  }

  @Override
  public ConnectionValidationResult validate(CustomLoggerConnection connection) {
    return ConnectionValidationResult.success();
  }
}
