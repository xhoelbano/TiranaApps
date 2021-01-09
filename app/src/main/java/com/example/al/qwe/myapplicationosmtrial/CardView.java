package com.example.al.qwe.myapplicationosmtrial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0003J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"},
        d2 = {"Landroidx/viewpager2/integration/testapp/cards/CardView;", "", "layoutInflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)V", "textCorner1", "Landroid/widget/TextView;", "textCorner2", "textSuite", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "bind", "", "card", "Landroidx/viewpager2/integration/testapp/cards/Card;", "getColor", "", "getColorRes", "getShade", "Companion", "ViewPager2.app"}
)
public final class CardView {
    @NotNull
    private final View view;
    private final TextView textSuite;
    private final TextView textCorner1;
    private final TextView textCorner2;
    private static final int[][] COLOR_MAP = (int[][])(new int[][]{{500185, 500023, 500083, 500130}, {500017, 500074, 500113, 500169}, {500157, 500100, 500056, 500006}, {500030, 500086, 500135, 500191}});
    @NotNull
    public static final CardView.Companion Companion = new CardView.Companion((DefaultConstructorMarker)null);

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final void bind(@NotNull Card card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        this.textSuite.setText((CharSequence)card.getSuit());
        this.view.setBackgroundResource(this.getColorRes(card));
        String cornerLabel = card.getCornerLabel();
        this.textCorner1.setText((CharSequence)cornerLabel);
        this.textCorner2.setText((CharSequence)cornerLabel);
    }

    @ColorRes
    private final int getColorRes(Card card) {
        int shade = this.getShade(card);
        int color = this.getColor(card);
        return COLOR_MAP[color][shade];
    }

    private final int getShade(Card card) {
        String var2 = card.getValue();
        switch(var2.hashCode()) {
            case 50:
                if (var2.equals("2")) {
                    return 2;
                }
                break;
            case 51:
                if (var2.equals("3")) {
                    return 3;
                }
                break;
            case 52:
                if (var2.equals("4")) {
                    return 0;
                }
                break;
            case 53:
                if (var2.equals("5")) {
                    return 1;
                }
                break;
            case 54:
                if (var2.equals("6")) {
                    return 2;
                }
                break;
            case 55:
                if (var2.equals("7")) {
                    return 3;
                }
                break;
            case 56:
                if (var2.equals("8")) {
                    return 0;
                }
                break;
            case 57:
                if (var2.equals("9")) {
                    return 1;
                }
                break;
            case 65:
                if (var2.equals("A")) {
                    return 2;
                }
                break;
            case 74:
                if (var2.equals("J")) {
                    return 3;
                }
                break;
            case 75:
                if (var2.equals("K")) {
                    return 1;
                }
                break;
            case 81:
                if (var2.equals("Q")) {
                    return 0;
                }
                break;
            case 1567:
                if (var2.equals("10")) {
                    return 2;
                }
        }

        throw (Throwable)(new IllegalStateException("Card value cannot be " + card + ".value"));
    }

    private final int getColor(Card card) {
        String var2 = card.getSuit();
        switch(var2.hashCode()) {
            case 9824:
                if (var2.equals("♠")) {
                    return 3;
                }
            case 9825:
            case 9826:
            case 9828:
            default:
                break;
            case 9827:
                if (var2.equals("♣")) {
                    return 0;
                }
                break;
            case 9829:
                if (var2.equals("♥")) {
                    return 2;
                }
                break;
            case 9830:
                if (var2.equals("♦")) {
                    return 1;
                }
        }

        throw (Throwable)(new IllegalStateException("Card suit cannot be " + card + ".suit"));
    }

    public CardView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup container) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "layoutInflater");
        super();
        View var10001 = layoutInflater.inflate(1300045, container, false);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "layoutInflater.inflate(R…layout, container, false)");
        this.view = var10001;
        var10001 = this.view.findViewById(1000259);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "view.findViewById(R.id.label_center)");
        this.textSuite = (TextView)var10001;
        var10001 = this.view.findViewById(1000021);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "view.findViewById(R.id.label_top)");
        this.textCorner1 = (TextView)var10001;
        var10001 = this.view.findViewById(1000189);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "view.findViewById(R.id.label_bottom)");
        this.textCorner2 = (TextView)var10001;
    }

    @Metadata(
            mv = {1, 1, 18},
            bv = {1, 0, 3},
            k = 1,
            d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"},
            d2 = {"Landroidx/viewpager2/integration/testapp/cards/CardView$Companion;", "", "()V", "COLOR_MAP", "", "", "[[I", "ViewPager2.app"}
    )
    public static final class Companion {
        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
