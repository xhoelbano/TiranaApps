package com.example.al.qwe.myapplicationosmtrial;


import android.os.Bundle;

import androidx.core.text.BidiFormatter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;


@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000f"},
        d2 = {"Landroidx/viewpager2/booleanegration/testapp/cards/Card;", "", "suit", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "cornerLabel", "getCornerLabel", "()Ljava/lang/String;", "getSuit", "getValue", "toBundle", "Landroid/os/Bundle;", "toString", "Companion", "ViewPager2.app"}
)
public final class Card {
    @NotNull
    private final String suit;
    @NotNull
    private final String value;
    @NotNull
    private static final String ARGS_BUNDLE = Card.class.getName() + ":Bundle";
    @NotNull
    private static final Set SUITS = SetsKt.setOf(new String[]{"♣", "♦", "♥", "♠"});
    @NotNull
    private static final Set VALUES = SetsKt.setOf(new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"});
    @NotNull
    private static final List DECK;
    @NotNull
    public static final Card.Companion Companion = new Card.Companion();

    @NotNull
    public final String getCornerLabel() {
        return this.value + "\n" + this.suit;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle args = new Bundle(1);
        args.putStringArray(ARGS_BUNDLE, new String[]{this.suit, this.value});
        return args;
    }

    @NotNull
    public String toString() {
        BidiFormatter bidi = BidiFormatter.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(bidi, "bidi");
        String var10000;
        if (!bidi.isRtlContext()) {
            var10000 = bidi.unicodeWrap(this.value + ' ' + this.suit);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "bidi.unicodeWrap(\"$value $suit\")");
            return var10000;
        } else {
            var10000 = bidi.unicodeWrap(this.suit + ' ' + this.value);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "bidi.unicodeWrap(\"$suit $value\")");
            return var10000;
        }
    }

    @NotNull
    public final String getSuit() {
        return this.suit;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    private Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    static {
        Iterable $this$flatMap$iv = (Iterable)SUITS;
        boolean $i$f$flatMap = false;
        Collection destination$iv$iv = (Collection)(new ArrayList());
        boolean $i$f$flatMapTo = false;
        Iterator var5 = $this$flatMap$iv.iterator();

        while(var5.hasNext()) {
            Object element$iv$iv = var5.next();
            String suit = (String)element$iv$iv;
            boolean var8 = false;
            Iterable $this$map$iv = (Iterable)VALUES;
            boolean $i$f$map = false;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            boolean $i$f$mapTo = false;
            Iterator var14 = $this$map$iv.iterator();

            while(var14.hasNext()) {
                Object item$iv$iv = var14.next();
                String value = (String)item$iv$iv;
                boolean var18 = false;
                Card var19 = new Card(suit, value);
                destination$iv$iv.add(var19);
            }

            Iterable list$iv$iv = (Iterable)((List)destination$iv$iv);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }

        DECK = (List)destination$iv$iv;
    }

    // $FF: synthetic method
    public Card(String suit, String value, DefaultConstructorMarker $constructor_marker) {
        this(suit, value);
    }

    @Metadata(
            mv = {1, 1, 18},
            bv = {1, 0, 3},
            k = 1,
            d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014J\"\u0010\u0015\u001a\u0004\u0018\u00010\t*\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0018"},
            d2 = {"Landroidx/viewpager2/booleanegration/testapp/cards/Card$Companion;", "", "()V", "ARGS_BUNDLE", "", "getARGS_BUNDLE$ViewPager2_app", "()Ljava/lang/String;", "DECK", "", "Landroidx/viewpager2/booleanegration/testapp/cards/Card;", "getDECK", "()Ljava/util/List;", "SUITS", "", "getSUITS", "()Ljava/util/Set;", "VALUES", "getVALUES", "fromBundle", "bundle", "Landroid/os/Bundle;", "find", "value", "suit", "ViewPager2.app"}
    )
    public static final class Companion {
        @NotNull
        public final String getARGS_BUNDLE$ViewPager2_app() {
            return Card.ARGS_BUNDLE;
        }

        @NotNull
        public final Set getSUITS() {
            return Card.SUITS;
        }

        @NotNull
        public final Set getVALUES() {
            return Card.VALUES;
        }

        @NotNull
        public final List getDECK() {
            return Card.DECK;
        }

        @Nullable
        public final Card find(@NotNull List $this$find, @NotNull String value, @NotNull String suit) {
            Intrinsics.checkParameterIsNotNull($this$find, "$this$find");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Intrinsics.checkParameterIsNotNull(suit, "suit");
            Iterable var4 = (Iterable)$this$find;
            boolean var5 = false;
            boolean var7 = false;
            Iterator var8 = var4.iterator();

            Object var10000;
            while(true) {
                if (!var8.hasNext()) {
                    var10000 = null;
                    break;
                }

                Object var9 = var8.next();
                Card it = (Card)var9;
                boolean var11 = false;
                if (Intrinsics.areEqual(it.getValue(), value) && Intrinsics.areEqual(it.getSuit(), suit)) {
                    var10000 = var9;
                    break;
                }
            }

            return (Card)var10000;
        }

        @NotNull
        public final Card fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String[] spec = bundle.getStringArray(((Card.Companion)this).getARGS_BUNDLE$ViewPager2_app());
            Card var10000 = new Card;
            if (spec == null) {
                Intrinsics.throwNpe();
            }

            String var10002 = spec[0];
            Intrinsics.checkExpressionValueIsNotNull(spec[0], "spec!![0]");
            String var10003 = spec[1];
            Intrinsics.checkExpressionValueIsNotNull(spec[1], "spec[1]");
            var10000.<init>(var10002, var10003, (DefaultConstructorMarker)null);
            return var10000;
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
