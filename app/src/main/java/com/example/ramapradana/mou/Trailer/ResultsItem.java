package com.example.ramapradana.mou.Trailer;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultsItem implements Parcelable {
	private String site;
	private int size;
	private String iso31661;
	private String name;
	private String id;
	private String type;
	private String iso6391;
	private String key;

	protected ResultsItem(Parcel in) {
		site = in.readString();
		size = in.readInt();
		iso31661 = in.readString();
		name = in.readString();
		id = in.readString();
		type = in.readString();
		iso6391 = in.readString();
		key = in.readString();
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	public void setSite(String site){
		this.site = site;
	}

	public String getSite(){
		return site;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	public String getIso31661(){
		return iso31661;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"site = '" + site + '\'' + 
			",size = '" + size + '\'' + 
			",iso_3166_1 = '" + iso31661 + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(site);
		parcel.writeInt(size);
		parcel.writeString(iso31661);
		parcel.writeString(name);
		parcel.writeString(id);
		parcel.writeString(type);
		parcel.writeString(iso6391);
		parcel.writeString(key);
	}
}
