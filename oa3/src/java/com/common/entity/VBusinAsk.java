/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinAsk
 *  com.common.entity.VBusinAskId
 */
package com.common.entity;

import com.common.entity.VBusinAskId;
import java.io.Serializable;

public class VBusinAsk
implements Serializable {
    private VBusinAskId id;

    public VBusinAsk() {
    }

    public VBusinAsk(VBusinAskId id) {
        this.id = id;
    }

    public VBusinAskId getId() {
        return this.id;
    }

    public void setId(VBusinAskId id) {
        this.id = id;
    }
}

