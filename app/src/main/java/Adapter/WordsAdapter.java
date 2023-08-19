package Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.soheil.walletgenerator.databinding.ItemWordListBinding;

import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import utils.Utils;

public class WordsAdapter extends ListAdapter<String, WordsAdapter.ViewHolder> {

    private static final String TAG = "WordsAdapter";
    private Utils utils ;

    @Inject
    public WordsAdapter(Utils utils) {
        super(DIFF_CALLBACK);
        this.utils = utils;
    }


    static DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemWordListBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word = getItem(position);

        holder.setWord(word);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ItemWordListBinding item ;

        public ViewHolder(ItemWordListBinding itemWordListBinding) {
            super(itemWordListBinding.getRoot());
            this.item = itemWordListBinding;
        }

        public void setWord (String word) {
            item.txtWordItemList.setText(word);
        }

    }
}
