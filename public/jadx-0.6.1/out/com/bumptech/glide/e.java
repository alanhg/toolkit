package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.e.a;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.a.d;
import com.bumptech.glide.request.b.j;

/* compiled from: GenericRequestBuilder */
public class e<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final g c;
    protected final Class<TranscodeType> d;
    protected final m e;
    protected final g f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private b i;
    private boolean j;
    private int k;
    private int l;
    private com.bumptech.glide.request.e<? super ModelType, TranscodeType> m;
    private Float n;
    private e<?, ?, ?, TranscodeType> o;
    private Float p;
    private Drawable q;
    private Drawable r;
    private Priority s;
    private boolean t;
    private d<TranscodeType> u;
    private int v;
    private int w;
    private DiskCacheStrategy x;
    private f<ResourceType> y;
    private boolean z;

    /* compiled from: GenericRequestBuilder */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    e(com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        this(eVar.b, eVar.a, fVar, cls, eVar.c, eVar.e, eVar.f);
        this.h = eVar.h;
        this.j = eVar.j;
        this.i = eVar.i;
        this.x = eVar.x;
        this.t = eVar.t;
    }

    e(Context context, Class<ModelType> cls, com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, g gVar, m mVar, g gVar2) {
        a aVar = null;
        this.i = com.bumptech.glide.f.a.a();
        this.p = Float.valueOf(1.0f);
        this.s = null;
        this.t = true;
        this.u = com.bumptech.glide.request.a.e.a();
        this.v = -1;
        this.w = -1;
        this.x = DiskCacheStrategy.RESULT;
        this.y = com.bumptech.glide.load.resource.d.b();
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = gVar;
        this.e = mVar;
        this.f = gVar2;
        if (fVar != null) {
            aVar = new a(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.d<DataType, ResourceType> dVar) {
        if (this.g != null) {
            this.g.a((com.bumptech.glide.load.d) dVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.a<DataType> aVar) {
        if (this.g != null) {
            this.g.a((com.bumptech.glide.load.a) aVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        this.x = diskCacheStrategy;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(Priority priority) {
        this.s = priority;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(f<ResourceType>... fVarArr) {
        this.z = true;
        if (fVarArr.length == 1) {
            this.y = fVarArr[0];
        } else {
            this.y = new c(fVarArr);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> h() {
        return a(com.bumptech.glide.request.a.e.a());
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> f(int i) {
        return a(new com.bumptech.glide.request.a.g(this.b, i));
    }

    e<ModelType, DataType, ResourceType, TranscodeType> a(d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> e(int i) {
        this.k = i;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> d(Drawable drawable) {
        this.q = drawable;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> d(int i) {
        this.l = i;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> c(Drawable drawable) {
        this.r = drawable;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.request.e<? super ModelType, TranscodeType> eVar) {
        this.m = eVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(boolean z) {
        this.t = !z;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(int i, int i2) {
        if (h.a(i, i2)) {
            this.w = i;
            this.v = i2;
            return this;
        }
        throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = bVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modelType) {
        this.h = modelType;
        this.j = true;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> g() {
        try {
            e<ModelType, DataType, ResourceType, TranscodeType> eVar = (e) super.clone();
            eVar.g = this.g != null ? this.g.g() : null;
            return eVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends j<TranscodeType>> Y b(Y y) {
        h.a();
        if (y == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (this.j) {
            com.bumptech.glide.request.b a = y.a();
            if (a != null) {
                a.d();
                this.e.b(a);
                a.a();
            }
            a = a((j) y);
            y.a(a);
            this.f.a(y);
            this.e.a(a);
            return y;
        } else {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        }
    }

    public j<TranscodeType> a(ImageView imageView) {
        h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        if (!(this.z || imageView.getScaleType() == null)) {
            switch (AnonymousClass2.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    f();
                    break;
                case 2:
                case 3:
                case 4:
                    e();
                    break;
            }
        }
        return b(this.c.a(imageView, this.d));
    }

    public com.bumptech.glide.request.a<TranscodeType> d(int i, int i2) {
        final com.bumptech.glide.request.a dVar = new com.bumptech.glide.request.d(this.c.g(), i, i2);
        this.c.g().post(new Runnable(this) {
            final /* synthetic */ e b;

            public void run() {
                if (!dVar.isCancelled()) {
                    this.b.b(dVar);
                }
            }
        });
        return dVar;
    }

    void f() {
    }

    void e() {
    }

    private Priority a() {
        if (this.s == Priority.LOW) {
            return Priority.NORMAL;
        }
        if (this.s == Priority.NORMAL) {
            return Priority.HIGH;
        }
        return Priority.IMMEDIATE;
    }

    private com.bumptech.glide.request.b a(j<TranscodeType> jVar) {
        if (this.s == null) {
            this.s = Priority.NORMAL;
        }
        return a(jVar, null);
    }

    private com.bumptech.glide.request.b a(j<TranscodeType> jVar, com.bumptech.glide.request.g gVar) {
        com.bumptech.glide.request.b gVar2;
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(com.bumptech.glide.request.a.e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (h.a(this.w, this.v) && !h.a(this.o.w, this.o.v)) {
                this.o.b(this.w, this.v);
            }
            gVar2 = new com.bumptech.glide.request.g(gVar);
            com.bumptech.glide.request.b a = a(jVar, this.p.floatValue(), this.s, gVar2);
            this.A = true;
            com.bumptech.glide.request.b a2 = this.o.a(jVar, gVar2);
            this.A = false;
            gVar2.a(a, a2);
            return gVar2;
        } else if (this.n == null) {
            return a(jVar, this.p.floatValue(), this.s, gVar);
        } else {
            gVar2 = new com.bumptech.glide.request.g(gVar);
            gVar2.a(a(jVar, this.p.floatValue(), this.s, gVar2), a(jVar, this.n.floatValue(), a(), gVar2));
            return gVar2;
        }
    }

    private com.bumptech.glide.request.b a(j<TranscodeType> jVar, float f, Priority priority, com.bumptech.glide.request.c cVar) {
        return GenericRequest.a(this.g, this.h, this.i, this.b, priority, jVar, f, this.q, this.k, this.r, this.l, this.B, this.C, this.m, cVar, this.c.b(), this.y, this.d, this.t, this.u, this.w, this.v, this.x);
    }
}
