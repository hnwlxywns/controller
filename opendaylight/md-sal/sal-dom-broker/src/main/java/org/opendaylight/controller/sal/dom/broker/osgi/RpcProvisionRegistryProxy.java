/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.controller.sal.dom.broker.osgi;

import java.util.Set;

import org.opendaylight.controller.md.sal.common.api.routing.RouteChangeListener;
import org.opendaylight.controller.sal.core.api.Broker;
import org.opendaylight.controller.sal.core.api.RoutedRpcDefaultImplementation;
import org.opendaylight.controller.sal.core.api.RpcImplementation;
import org.opendaylight.controller.sal.core.api.RpcProvisionRegistry;
import org.opendaylight.controller.sal.core.api.RpcRegistrationListener;
import org.opendaylight.controller.sal.core.api.RpcRoutingContext;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.data.api.CompositeNode;
import org.opendaylight.yangtools.yang.data.api.InstanceIdentifier;
import org.osgi.framework.ServiceReference;

import com.google.common.util.concurrent.ListenableFuture;

public class RpcProvisionRegistryProxy extends AbstractBrokerServiceProxy<RpcProvisionRegistry> implements RpcProvisionRegistry {

    public RpcProvisionRegistryProxy(final ServiceReference<RpcProvisionRegistry> ref, final RpcProvisionRegistry delegate) {
        super(ref, delegate);
    }

    @Override
    public Broker.RpcRegistration addRpcImplementation(final QName rpcType, final RpcImplementation implementation) throws IllegalArgumentException {
        return getDelegate().addRpcImplementation(rpcType, implementation);
    }

    @Override
    public ListenerRegistration<RpcRegistrationListener> addRpcRegistrationListener(final RpcRegistrationListener listener) {
        return getDelegate().addRpcRegistrationListener(listener);
    }

    @Override
    public Broker.RoutedRpcRegistration addRoutedRpcImplementation(final QName rpcType, final RpcImplementation implementation) {
        return getDelegate().addRoutedRpcImplementation(rpcType, implementation);
    }

    @Override
    public void setRoutedRpcDefaultDelegate(final RoutedRpcDefaultImplementation defaultImplementation) {
        getDelegate().setRoutedRpcDefaultDelegate(defaultImplementation);
    }

    @Override
    public <L extends RouteChangeListener<RpcRoutingContext, InstanceIdentifier>> ListenerRegistration<L> registerRouteChangeListener(final L listener) {
        return getDelegate().registerRouteChangeListener(listener);
    }

    @Override
    public Set<QName> getSupportedRpcs() {
        return getDelegate().getSupportedRpcs();
    }

    @Override
    public ListenableFuture<RpcResult<CompositeNode>> invokeRpc(final QName rpc, final CompositeNode input) {
        return getDelegate().invokeRpc(rpc, input);
    }
}
