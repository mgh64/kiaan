
var m = function() {
    if ( this.persons ){
	for (var idx = 0; idx < this.persons.length; idx++) {
	    var key = this.persons[idx]._id;
	    var value = {
			    person_id: this.persons[idx]._id,
			    personId: this.persons[idx].id,
			    g_id: this._id,
			    g_name: this.name,
			    type: this.persons[idx].type,
			    first_name: this.persons[idx].first_name,
			    last_name: this.persons[idx].last_name,
			    credit_limit: this.persons[idx].credit_limit || 0,
			    address: this.persons[idx].address || "",
			    comment: this.persons[idx].comment
			};
	    if (this.persons[idx].tel){
		for (var i=0; i < this.persons[idx].tel.length; i++){
		    if (this.persons[idx].tel[i].default){
			value[this.persons[idx].tel[i].type] = this.persons[idx].tel[i].number;
		    }
		}
		if (!value.mobile) value.mobile = "";
		if (!value.work) value.work = "";
	    } else {
		value.mobile = "";
		value.work = ""
	    }
	    if (this.persons[idx].bankAcc){
		for (var j=0; j < this.persons[idx].bankAcc.length; j++){
		    if (this.persons[idx].bankAcc[j].default){
			value.bankAcc = this.persons[idx].bankAcc[j];
		    }
		}
	    } else {
		value.bankAcc = {};
	    }
	    emit(key, value);
	}
    }     
};

var r = function(key, values) {    
    return values;
};

db.persons.mapReduce(m,r,{out: "mp"})
db.mp.find().pretty();
db.mp.drop(); 
