package com.tugalsan.api.math.client;

import java.util.Objects;

public class TGS_MathMatrix2x2<T> {

    public TGS_MathMatrix2x2(T x00, T x01, T x10, T x11) {
        this.x00 = x00;
        this.x01 = x01;
        this.x10 = x10;
        this.x11 = x11;
    }

    @Override
    public String toString() {
        return TGS_MathMatrix2x2.class.getSimpleName() + "{" + "x00=" + x00 + ", x01=" + x01 + ", x10=" + x10 + ", x11=" + x11 + '}';
    }

    public T x00, x01, x10, x11;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TGS_MathMatrix2x2<?> other = (TGS_MathMatrix2x2<?>) obj;
        if (!Objects.equals(this.x00, other.x00)) {
            return false;
        }
        if (!Objects.equals(this.x01, other.x01)) {
            return false;
        }
        if (!Objects.equals(this.x10, other.x10)) {
            return false;
        }
        return Objects.equals(this.x11, other.x11);
    }

}
