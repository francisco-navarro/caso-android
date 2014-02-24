package app.prueba.caso_android.android.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.prueba.caso_android.R;
import app.prueba.caso_android.epub.BookItem;

public class ListBookAdapter extends BaseAdapter{
	
	private List<BookItem> bookList;
	private Context appContext;


	public ListBookAdapter (final Context appContext, final List<BookItem> plist)
	{
		super();
		this.bookList = plist;
		this.appContext = appContext;
	}
	
	public ListBookAdapter (final Context appContext, final String[] plist)
	{
		super();
		this.bookList = new ArrayList<BookItem>();
		
		for(String item:plist)
			bookList.add(new BookItem(item));
		
		
		this.appContext = appContext;
	}

	public List<BookItem> getPlist() {
		return bookList;
	}

	public void setPlist(final List<BookItem> plist) {
		this.bookList = plist;
	}

	public Context getAppContext() {
		return appContext;
	}

	public void setAppContext(final Context appContext) {
		this.appContext = appContext;
	}

	public int getCount() {
		return bookList.size();
	}

	public Object getItem(final int idx) {
		return bookList.get(idx);
	}

	public long getItemId(final int id) {
		return id;
	}

	
	public View getView(final int indx, final View recycled, final ViewGroup arg2)
	{
		View view = null;
		if (recycled == null){
			
			final LayoutInflater inflater = (LayoutInflater)appContext.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.book_item, null);

		} else {
			
			view = recycled;
		}
			
		TextView nombre = (TextView) view.findViewById(R.id.textViewNombre);
		TextView fecha = (TextView) view.findViewById(R.id.textViewFecha);
		
		BookItem book = bookList.get(indx);
				
		nombre.setText(book.getNombre());
		fecha.setText(book.getFecha());

		//TODO: coger y ver el tamaño

		return view;
	}

	
}
