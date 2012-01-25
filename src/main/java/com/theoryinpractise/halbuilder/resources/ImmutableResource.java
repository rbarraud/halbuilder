package com.theoryinpractise.halbuilder.resources;

import com.google.common.collect.Multimap;
import com.theoryinpractise.halbuilder.ReadableResource;
import com.theoryinpractise.halbuilder.Resource;

import java.util.Map;

public class ImmutableResource extends MutableResource {

    public ImmutableResource(String href, Map<String, String> namespaces, Multimap<String, String> links, Map<String, Object> properties, Multimap<String, ReadableResource> resources) {
        super(null, href);
        this.namespaces = namespaces;
        this.links = links;
        this.properties = properties;
        this.resources = resources;
    }

    @Override
    public MutableResource withLink(String rel, String url) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withProperty(String name, Object value) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withBean(Object value) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withFields(Object value) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withFieldBasedSubresource(String rel, String href, Object o) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withBeanBasedSubresource(String rel, String href, Object o) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public Resource withNamespace(String namespace, String url) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }

    @Override
    public MutableResource withSubresource(String rel, ReadableResource resource) {
        throw new UnsupportedOperationException("ImmutableResources cannot be mutated.");
    }
}
