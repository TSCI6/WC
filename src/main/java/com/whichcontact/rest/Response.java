package com.whichcontact.rest;

public class Response {

	private String version;
	private String encoding;
	// private List<Feed> feed;

	class Feed {
		class Entry {
			class Id {
				private String $t;

			}

			class title {

				private String $t;

			}

			class gd$email {

				private String address;

			}

			class gd$phoneNumber {

				private String $t;

			}
		}

	}

}
