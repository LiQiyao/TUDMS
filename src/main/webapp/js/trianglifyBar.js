var test = document.getElementsByClassName("tri");
for(i=0;i<test.length;i++){
	var pattern = Trianglify({height: 20,width: 5000,cell_size: 100});
	test[i].appendChild(pattern.canvas());
}