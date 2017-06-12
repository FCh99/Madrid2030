package com.example.faustocheca.madrid2030;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by faustocheca on 28/4/16.
 */
public class Local implements Parcelable {

    String name;
    String activity;
    String zone;
    String direccion;
    String telefono;
    String comentarios;
    String coordenadas;
    String foto1;


    public Local(String name, String activity, String zone, String comments, String coords,
                 String direccion, String phone, String foto1) {
        this.name = name;
        this.activity = activity;
        this.zone = zone;
        this.direccion = direccion;
        this.telefono=phone;
        this.comentarios = comments;
        this.coordenadas=coords;
        this.foto1 = foto1;
    }

    protected Local(Parcel in) {
        name = in.readString();
        activity = in.readString();
        zone = in.readString();
        direccion = in.readString();
        telefono = in.readString();
        comentarios = in.readString();
        coordenadas=in.readString();
        foto1=in.readString();
    }

    public static final Creator<Local> CREATOR = new Creator<Local>() {
        @Override
        public Local createFromParcel(Parcel in) {
            return new Local(in);
        }

        @Override
        public Local[] newArray(int size) {
            return new Local[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getActivity() {
        return activity;
    }



    public String getZone() {
        return zone;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public String getFoto1() {
        return foto1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(activity);
        dest.writeString(zone);
        dest.writeString(direccion);
        dest.writeString(telefono);
        dest.writeString(comentarios);
        dest.writeString(coordenadas);
        dest.writeString(foto1);
    }

    @Override

    // OJO no está foto1

    public String toString() {
        return getName()+" "+getActivity()+" "+getZone()+" "+getDireccion()+" "
                +getTelefono()+" "+getComentarios()+" "+getCoordenadas()+" "+getFoto1();
    }
}
