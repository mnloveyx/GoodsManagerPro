package com.panli.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
		String value;
	    String key;
	     
	    /**
	     * {@inheritDoc}
	     * @see java.lang.Object#toString()
	     */
	    public String toString() {
	        return key;
	    }

		public Item(String key,String value) {
			super();
			this.value = value;
			this.key = key;
		}
		public Item() {
		}
}
