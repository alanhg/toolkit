package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import com.bumptech.glide.i;
import java.util.HashSet;

@TargetApi(11)
/* compiled from: RequestManagerFragment */
public class j extends Fragment {
    private final a a;
    private final l b;
    private i c;
    private final HashSet<j> d;
    private j e;

    /* compiled from: RequestManagerFragment */
    private class a implements l {
        final /* synthetic */ j a;

        private a(j jVar) {
            this.a = jVar;
        }
    }

    public j() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    j(a aVar) {
        this.b = new a();
        this.d = new HashSet();
        this.a = aVar;
    }

    public void a(i iVar) {
        this.c = iVar;
    }

    a a() {
        return this.a;
    }

    public i b() {
        return this.c;
    }

    public l c() {
        return this.b;
    }

    private void a(j jVar) {
        this.d.add(jVar);
    }

    private void b(j jVar) {
        this.d.remove(jVar);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.e = k.a().a(getActivity().getFragmentManager());
            if (this.e != this) {
                this.e.a(this);
            }
        } catch (IllegalStateException e) {
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.a.a();
    }

    public void onStop() {
        super.onStop();
        this.a.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.c();
    }

    public void onTrimMemory(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void onLowMemory() {
        if (this.c != null) {
            this.c.a();
        }
    }
}
