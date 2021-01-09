package com.example.al.qwe.myapplicationosmtrial;
// CardViewHolder.java

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import org.jetbrains.annotations.NotNull;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"},
        d2 = {"Landroidx/viewpager2/integration/testapp/cards/CardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "cardView", "Landroidx/viewpager2/integration/testapp/cards/CardView;", "(Landroidx/viewpager2/integration/testapp/cards/CardView;)V", "bind", "", "card", "Landroidx/viewpager2/integration/testapp/cards/Card;", "bind$ViewPager2_app", "ViewPager2.app"}
)
public final class CardViewHolder extends ViewHolder {
    private final CardView cardView;

    public final void bind$ViewPager2_app(@NotNull Card card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        this.cardView.bind(card);
    }

    public CardViewHolder(@NotNull CardView cardView) {
        Intrinsics.checkParameterIsNotNull(cardView, "cardView");
        super(cardView.getView());
        this.cardView = cardView;
    }
}
// CardViewAdapter.java



@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"},
        d2 = {"Landroidx/viewpager2/integration/testapp/cards/CardViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/viewpager2/integration/testapp/cards/CardViewHolder;", "()V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewPager2.app"}
)
public final class CardViewAdapter extends Adapter {
    @NotNull
    public CardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        LayoutInflater var10004 = LayoutInflater.from(parent.getContext());
        Intrinsics.checkExpressionValueIsNotNull(var10004, "LayoutInflater.from(parent.context)");
        return new CardViewHolder(new CardView(var10004, parent));
    }

    // $FF: synthetic method
    // $FF: bridge method
    public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
        return (ViewHolder)this.onCreateViewHolder(var1, var2);
    }

    public void onBindViewHolder(@NotNull CardViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        holder.bind$ViewPager2_app((Card)Card.Companion.getDECK().get(position));
    }

    // $FF: synthetic method
    // $FF: bridge method
    public void onBindViewHolder(ViewHolder var1, int var2) {
        this.onBindViewHolder((CardViewHolder)var1, var2);
    }

    public int getItemCount() {
        return Card.Companion.getDECK().size();
    }
}

