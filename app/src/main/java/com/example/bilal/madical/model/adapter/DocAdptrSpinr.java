package com.example.bilal.madical.model.adapter;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.example.bilal.madical.R;
        import com.example.bilal.madical.model.pojo.Doctor;

        import java.util.List;

public class DocAdptrSpinr extends ArrayAdapter<Doctor> {

    private Context context;
    private List<Doctor> docList;

    public DocAdptrSpinr(Context context, int resource, List<Doctor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.docList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.docspinr, parent, false);
        Doctor doctor = docList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(doctor.getName());
        return view;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.docspinr, parent, false);
        Doctor doctor = docList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(doctor.getName());
        return view;
    }
}
