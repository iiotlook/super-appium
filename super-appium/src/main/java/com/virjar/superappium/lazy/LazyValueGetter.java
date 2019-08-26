package com.virjar.superappium.lazy;

import com.virjar.superappium.ViewModel;

public class LazyValueGetter<T> implements ValueGetter {
    private ValueGetter<T> delegate;
    private T theValue;
    private boolean hasDelegateCalled = false;

    public LazyValueGetter(ValueGetter<T> delegate) {
        this.delegate = delegate;
    }


    @Override
    public boolean support(Class type) {
        return delegate.support(type);
    }

    @Override
    public Object get(ViewModel viewModel) {
        if (hasDelegateCalled) {
            return theValue;
        }
        synchronized (this) {
            if (hasDelegateCalled) {
                return theValue;
            }
            theValue = delegate.get(viewModel);
            hasDelegateCalled = true;
        }
        return theValue;
    }

    @Override
    public String attr() {
        return delegate.attr();
    }
}
