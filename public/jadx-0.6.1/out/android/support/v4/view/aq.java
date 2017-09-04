package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* compiled from: ViewPropertyAnimatorCompatJB */
class aq {
    public static void a(final View view, final ar arVar) {
        if (arVar != null) {
            view.animate().setListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    arVar.c(view);
                }

                public void onAnimationEnd(Animator animator) {
                    arVar.b(view);
                }

                public void onAnimationStart(Animator animator) {
                    arVar.a(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }
}
