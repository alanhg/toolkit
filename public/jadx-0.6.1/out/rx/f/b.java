package rx.f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.exceptions.a;
import rx.f;

/* compiled from: CompositeSubscription */
public final class b implements f {
    private Set<f> a;
    private volatile boolean b;

    public boolean isUnsubscribed() {
        return this.b;
    }

    public void a(f fVar) {
        if (!fVar.isUnsubscribed()) {
            if (!this.b) {
                synchronized (this) {
                    if (!this.b) {
                        if (this.a == null) {
                            this.a = new HashSet(4);
                        }
                        this.a.add(fVar);
                        return;
                    }
                }
            }
            fVar.unsubscribe();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(rx.f r2) {
        /*
        r1 = this;
        r0 = r1.b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r1);
        r0 = r1.b;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r1.a;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r1.a;	 Catch:{ all -> 0x001c }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x001c }
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x000e;
    L_0x0018:
        r2.unsubscribe();
        goto L_0x000e;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.f.b.b(rx.f):void");
    }

    public void unsubscribe() {
        if (!this.b) {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                this.b = true;
                Collection collection = this.a;
                this.a = null;
                a(collection);
            }
        }
    }

    private static void a(Collection<f> collection) {
        if (collection != null) {
            List list = null;
            for (f unsubscribe : collection) {
                try {
                    unsubscribe.unsubscribe();
                } catch (Throwable th) {
                    List arrayList;
                    if (list == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list;
                    }
                    arrayList.add(th);
                    list = arrayList;
                }
            }
            a.a(list);
        }
    }
}
