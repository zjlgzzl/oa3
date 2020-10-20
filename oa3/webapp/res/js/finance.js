function addDetail(){
	
	//明细数量
	detailCount = Number(detailCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	
	//序号
	var td1 = document.createElement("td");
	xuh ++;
	td1.innerHTML = '<input type="text" id="xuh' + detailCount + '"' +
					'		value="' + xuh + '"' +
					'		style="width:96px;border:0px;text-align:center;" disabled="disabled"/>';
	//alert(td1.innerHTML);
	tr.appendChild(td1);
	
	//金额
	var td2 = document.createElement("td");
	td2.innerHTML = '<input type="text" id="CMoney' + detailCount + '" name="detail[' + detailCount + '].CMoney"' +
					'		 class="field border" style="width:146px;"/>';
	tr.appendChild(td2);
	
	//备注
	var td3 = document.createElement("td");
	if (fun508 == 1){
		td3.innerHTML = '<input type="text" id="CRemarks' + detailCount + '" name="detail[' + detailCount + '].CRemarks"' +
						'		class="field border" style="width:352px;"/>';
	}else{
		td3.innerHTML = '';
	}
	
	tr.appendChild(td3);
	
	//删除
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="button" value="删除" style="width:60px; height:20px;"' +
					'		onclick = "delDetail(this)" />';
	tr.appendChild(td4);
	
	document.getElementById("detail").appendChild(tr);

}

function addDetailOut(){
	
	//明细数量
	detailCount = Number(detailCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	
	//序号
	var td1 = document.createElement("td");
	xuh ++;
	td1.innerHTML = '<input type="text" id="xuh' + detailCount + '"' +
					'		value="' + xuh + '"' +
					'		style="width:96px;border:0px;text-align:center;" disabled="disabled"/>';
	//alert(td1.innerHTML);
	tr.appendChild(td1);
	
	//金额
	var td2 = document.createElement("td");
	td2.innerHTML = '<input type="text" id="CMoney' + detailCount + '" name="detail[' + detailCount + '].CMoney"' +
					'		 class="field border" style="width:146px;"/>';
	tr.appendChild(td2);
	
	//支出选项
	/*
	var td11 = document.createElement("td");
	td11.innerHTML = '<select id="CItem' + detailCount + '"' + 
					 '		name="detail[' + detailCount + '].CItem" ' +
					 '		class="border" style="width:96px;"> ' +
					 '	<option value="-1">--请选择--</option> ' +
					 '	<option value="临时支出">临时支出</option> ' +
					 '	<option value="永久支出">永久支出</option>' +
					 '</select>';
	tr.appendChild(td11);*/
	
	//备注
	var td3 = document.createElement("td");
	if (fun509 == 1){
		td3.innerHTML = '<input type="text" id="CRemarks' + detailCount + '" name="detail[' + detailCount + '].CRemarks"' +
						'		class="field border" style="width:352px;"/>';
	}else{
		td3.innerHTML = '';
	}
	
	tr.appendChild(td3);
	
	//是否提醒
	/*
	var td5 = document.createElement("td");
	td5.innerHTML = '<select id="CNotice' + detailCount + '"' + 
					 '		name="detail[' + detailCount + '].CNotice" ' +
					 '		class="border" style="width:96px;"> ' +
					 '	<option value="1">提醒</option> ' +
					 '	<option value="2">不提醒</option>' +
					 '</select>';
	tr.appendChild(td5);*/
	
	//删除
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="button" value="删除" style="width:60px; height:20px;"' +
					'		onclick = "delDetail(this)" />';
	tr.appendChild(td4);
	
	document.getElementById("detail").appendChild(tr);

}

function delDetail(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
		//重新对序号赋值
		dealXuh();
	}
}

//重新对序号赋值
function dealXuh(){
	xuh = 0;
	for(var i=0; i<=detailCount; i++){
		if (document.getElementById("xuh" + i.toString()) == null){
			continue;
		}
		xuh ++;
		document.getElementById("xuh" + i.toString()).value = xuh;
	}
}