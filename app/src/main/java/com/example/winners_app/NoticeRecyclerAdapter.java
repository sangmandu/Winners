import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.winners_app.R;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.ViewHolder> {
    private ArrayList<NoticeRecycler> mData = null ;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    NoticeRecyclerAdapter(ArrayList<NoticeRecycler> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public NoticeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.notice_recycler, parent, false) ;
        NoticeRecyclerAdapter.ViewHolder vh = new NoticeRecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(NoticeRecyclerAdapter.ViewHolder holder, int position) {

        NoticeRecycler item = mData.get(position) ;

        holder.icon.setImageDrawable(item.getIcon()) ;
        holder.title.setText(item.getTitle()) ;
        holder.contents.setText(item.getContents()) ;
        holder.created.setText(item.getCreated()) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon ;
        TextView title ;
        TextView contents ;
        TextView created ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            icon = itemView.findViewById(R.id.icon) ;
            title = itemView.findViewById(R.id.title) ;
            contents = itemView.findViewById(R.id.contents) ;
            created = itemView.findViewById(R.id.created) ;
        }
    }
}
