package fr.isen.pecorella.androidcontactds

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var list: ArrayList<Results>, private val OnItemClickListener: (Results) -> Unit): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentName: TextView = view.findViewById(R.id.nomTitle)
        val contentAdress : TextView = view.findViewById(R.id.adresse)
        val contentMail : TextView = view.findViewById(R.id.adresseMail)
        //val picture : ImageView = view.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return MyViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = list[position]
        holder.contentName.text = items.name?.first + " " + (items.name?.last ?: "")
        holder.contentAdress.text = items.location?.state + " " + items.location?.street
        holder.contentMail.text = items.email

        holder.itemView.setOnClickListener { OnItemClickListener(items) }
    }

    override fun getItemCount(): Int = list.size

    fun refreshList(contactfromApi: ArrayList<Results>) {
        list =  contactfromApi
        notifyDataSetChanged()
    }
}