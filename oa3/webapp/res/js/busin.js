function thousandBitSeparator(num) {
	num=num.toString().split(".");  // 分隔小数点
    var arr=num[0].split("").reverse();  // 转换成字符数组并且倒序排列
    var res=[];
    for(var i=0,len=arr.length;i<len;i++){
      if(i%3===0&&i!==0){
         res.push(",");   // 添加分隔符
      }
      res.push(arr[i]);
    }
    res.reverse(); // 再次倒序成为正确的顺序
    if(num[1]){  // 如果有小数的话添加小数部分
      res=res.join("").concat("."+num[1]);
    }else{
      res=res.join("");
    }
    return res;
}


/*增加货柜*/
function addContainer(count){
	
	//货柜数量
	conCount = Number(conCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "conTR" + conCount.toString();
	
	//货柜号码
	var td1 = document.createElement("td");
	//if (conCount == 0){
		//td1.innerHTML = '<label><span style="color:red;">*</span>货柜号码</label>';
	//}else{
		td1.innerHTML = '';
	//}
	tr.appendChild(td1);
	
	//货柜号码编辑框
	var td2 = document.createElement("td");
	td2.innerHTML = '<input type="text" id="conNum' + conCount + '" name="cons[' + conCount + '].CContainerNum"' +
					'		 class="field border" style="width:96px;"/>';
	tr.appendChild(td2);
	
	//货柜类型
	var td3 = document.createElement("td");
	//if (conCount == 0){
		//td3.innerHTML = '<label><span style="color:red;">*</span>货柜类型</label>';
	//}else{
		td3.innerHTML = '';
	//}
	tr.appendChild(td3);
	
	//货柜类型编辑框
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="conType' + conCount + '" name="cons[' + conCount + '].CContainerType"' +
	  				'		  class="field border" style="width:76px;"/>';
	tr.appendChild(td4);
	
	//件数
	var td6 = document.createElement("td");
	td6.innerHTML = '';
	tr.appendChild(td6);
	
	var td7 = document.createElement("td");
	td7.innerHTML = '<input type="text" id="CCount' + conCount + '" name="cons[' + conCount + '].CCount"' +
					'class="field border" style="width:128px;"/>';
	tr.appendChild(td7);
	
	//备注
	var td10 = document.createElement("td");
	td10.innerHTML = '';
	tr.appendChild(td10);
	
	var td11 = document.createElement("td");
	if (fun203 == 1){
		td11.innerHTML = '<input type="text" id="CRemarks' + conCount + '" name="cons[' + conCount + '].CRemarks"' +
						 ' class="field border" style="width:56px;"/>';
	}else{
		td11.innerHTML = '';
	}
	tr.appendChild(td11);
	
	//备注2
	var td102 = document.createElement("td");
	td102.innerHTML = '';
	tr.appendChild(td102);
	
	var td101 = document.createElement("td");
	if (fun207 == 1){
		td101.innerHTML += '<input type="text" name="cons[' + conCount + '].CRemarks2"' +
		 					' class="field border" style="width:56px;"/>';
	}else{
		td101.innerHTML = '';
	}
	tr.appendChild(td101);
	
	//truck
	var td12 = document.createElement("td");
	td12.innerHTML = '';
	tr.appendChild(td12);
	
	var td13 = document.createElement("td");
	td13.innerHTML = '<input type="text" id="CTrucker' + conCount + '" name="cons[' + conCount + '].CTrucker"' +
	 				 'class="field border" style="width:121px;"/>';
	
	tr.appendChild(td13);
	
	
	var td5 = document.createElement("td");
	td5.innerHTML = '<input type="button" value="DELETE" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delContainer(this)" />';
					
	td5.setAttribute("colspan", 4);
	tr.appendChild(td5);
	
	document.getElementById("con").appendChild(tr);

	//增加行
	/*
	var conTable = document.getElementById("conTable");
	var rows = conTable.rows.length;
	var k = 0;
	for (var i=0; i<rows; i++){
		var trid = conTable.rows[i].id;
		if (trid == "conTR"+count.toString()){
			k = i + 1;
			break;
		}
	}
	//if (k == rows){
		document.getElementById("con").appendChild(tr);
	//}else{
	//	var nextid = conTable.rows[k].id.toString();
	//	var t = document.getElementById(nextid);
	//	t.parentNode.insertBefore(tr, t);
	//}*/
}

/*删除货柜*/
function delContainer(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
	}
}

/*添加服务项目*/
function addService(count){
	
	//服务项目数量
	serviceCount = Number(serviceCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "serviceTR" + serviceCount.toString();
	
	//服务类型
	var td1 = document.createElement("td");
	td1.innerHTML = '';
	tr.appendChild(td1);
	
	//服务类型编辑框
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TServiceType' + serviceCount + '"' +  
					'		 name="services[' + serviceCount + '].TServiceType.CId"' +
					'		 class="border" style="width:146px;" onchange="selectType(' + serviceCount + ')">' +
					'	<option value="0">PLEASE CHOOSE</option>' + 	
					'</select>';
	tr.appendChild(td2);
	
	//服务项目
	var td3 = document.createElement("td");
	td3.innerHTML = '';
	tr.appendChild(td3);
	
	//服务项目编辑框
	var td4 = document.createElement("td");
	td4.innerHTML = '<select id="TServiceItem' + serviceCount + '"' +  
					'		 name="services[' + serviceCount + '].TServiceItem.CId"' +
					'		 class="border" style="width:146px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' + 
					'</select>';
	tr.appendChild(td4);
	
	//件数
	var td5 = document.createElement("td");
	td5.innerHTML = '';
	tr.appendChild(td5);
	
	var td6 = document.createElement("td");
	/*
	td6.innerHTML = '<input type="text" id="CCount' + serviceCount + '" name="services[' + serviceCount + '].CCount"' + 
					' 		class="field border" style="width:106px;"/>';*/
	td6.innerHTML = '';
	tr.appendChild(td6);
	
	//重量
	var td7 = document.createElement("td");
	td7.innerHTML = '';
	tr.appendChild(td7);
	
	var td8 = document.createElement("td");
	/*
	td8.innerHTML = '<input type="text" id="CWeight' + serviceCount + '" name="services[' + serviceCount + '].CWeight"' + 
					' 		class="field border" style="width:106px;"/>';*/
	td8.innerHTML = '';
	tr.appendChild(td8);
	
	//增加行、删除行
	var td9 = document.createElement("td");
	td9.innerHTML = '<input type="button" value="增加行" style="float:left; margin-left:13px; width:60px; height:20px;"' + 
					' 		onclick = "addService(' + serviceCount + ');" />' +
					'<input type="button" value="删除行" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delService(this)" />';
	tr.appendChild(td9);

	//增加行
	var serviceTable = document.getElementById("serviceTable");
	var rows = serviceTable.rows.length;
	var k = 0;
	for (var i=0; i<rows; i++){
		var trid = serviceTable.rows[i].id;
		if (trid == "serviceTR"+count.toString()){
			k = i + 1;
			break;
		}
	}
	/*
	if (k == rows){
		document.getElementById("service").appendChild(tr);
	}else{
		var nextid = serviceTable.rows[k].id.toString();
		var t = document.getElementById(nextid);
		t.parentNode.insertBefore(tr, t);
	}*/
	document.getElementById("service").appendChild(tr);
	
	getServiceType(serviceCount);
	
}

/*删除服务项目*/
function delService(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
	}
}

/*增加成本*/
function addCost(count){
	
	var tb = document.getElementById("costTable");
	var rowcount = tb.rows.length;
	
	//成本数量
	costCount = Number(costCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "costTR" + costCount.toString();
	
	//成本项目
	/*
	var td1 = document.createElement("td");
	td1.innerHTML = '';
	tr.appendChild(td1);*/
	
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TCostItem' + costCount + '"' +  
					'		 name="costs[' + costCount + '].TCostItem.CId"' +
					'		 class="border _select" style="width:266px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>' +
					'<input type="hidden" id="costOrder' + costCount + '"' + 
					'		 name="costs[' + costCount + '].COrder"' + '/>' +
					'<input type="hidden" id="CGroupid' + costCount + '"' + 
					'		 name="costs[' + costCount + '].TCostGroup.CId"' + '/>';
	tr.appendChild(td2);
	
	//金额
	var td3 = document.createElement("td");
	td3.innerHTML = '';
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CMoney' + costCount + '" ' +
					'		name="costs[' + costCount +'].CMoney" ' +
					'		class="field border" style="width:76px;"/>' +
					'<input id="money_' + costCount + '" type="checkbox"' +
					'       style="margin-left:3px;width:15px;height:15px;"' +
					'       onclick="computeTmpMoney();" />';
	tr.appendChild(td4);
	
	//金额
	var td3 = document.createElement("td");
	td3.innerHTML = '';
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CRiel' + costCount + '" ' +
					'		name="costs[' + costCount +'].CRiel" ' +
					'		class="field border" style="width:86px;"/>';
	tr.appendChild(td4);
	
	//领款人
	var td5 = document.createElement("td");
	td5.innerHTML = '';
	tr.appendChild(td5);
	
	var td6 = document.createElement("td");
	/*
	td6.innerHTML = '<select id="TUser' + costCount + '"' +  
					'		 name="costs[' + costCount + '].TUserByCUserid.CId"' +
					'		 class="border" style="width:169px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>';*/
	td6.innerHTML = '<input type="text" id="CRe' + costCount + '" ' +
					'		name="costs[' + costCount +'].CRe" ' +
					'		class="field border" style="width:111px;"/>';
	tr.appendChild(td6);
	
	//备注
	var td7 = document.createElement("td");
	td7.innerHTML = '';
	tr.appendChild(td7);
	
	var td8 = document.createElement("td");
	//if (fun228 == 1 || fun205 == 1){
	if (fun205 == 1 || (fun228 != null && fun228 == 1)){
		td8.innerHTML = '<input type="text" id="CRemarks' + costCount + '" ' +
		'		name="costs[' + costCount +'].CRemarks" ' +
		'		class="field border" style="width:53px;"/>';
	}else{
		td8.innerHTML = '';
	}
	tr.appendChild(td8);
	
	//备注2
	var td77 = document.createElement("td");
	td77.innerHTML = '';
	tr.appendChild(td77);
	
	var td88 = document.createElement("td");
	//if (fun228 == 1 || fun208 == 1){
	if (fun208 == 1 || (fun228 != null && fun228 == 1)){
		td88.innerHTML = '<input type="text" ' +
		'		name="costs[' + costCount +'].CRemarks2" ' +
		'		class="field border" style="width:53px;"/>';
	}else{
		td88.innerHTML = '';
	}
	tr.appendChild(td88);
	
	//备注3
	td77 = document.createElement("td");
	td77.innerHTML = '';
	tr.appendChild(td77);
	
	td88 = document.createElement("td");
	//if (fun228 == 1 || fun216 == 1){
	if (fun216 == 1 || (fun228 != null && fun228 == 1)){
		td88.innerHTML = '<input type="text" ' +
		'		name="costs[' + costCount +'].CRemarks3" ' +
		'		class="field border" style="width:53px;"/>';
	}else{
		td88.innerHTML = '';
	}
	tr.appendChild(td88);
	
	//审核
	td77 = document.createElement("td");
	td77.innerHTML = '';
	tr.appendChild(td77);
	
	td88 = document.createElement("td");
	//if (fun228 == 1 || fun224 == 1){
	if (fun224 == 1 || (fun228 != null && fun228 == 1)){
		td88.innerHTML = '<input type="text" ' +
		'		name="costs[' + costCount +'].CRemarks5" ' +
		'		class="field border" style="width:53px;"/>';
	}else{
		td88.innerHTML = '';
	}
	tr.appendChild(td88);
	
	//备注4
	td77 = document.createElement("td");
	td77.innerHTML = '';
	tr.appendChild(td77);
	
	td88 = document.createElement("td");
	//if (fun228 == 1 || fun229 == 1){
	if (fun229 == 1 || (fun228 != null && fun228 == 1)){
		td88.innerHTML = '<input type="text" ' +
		'		name="costs[' + costCount +'].CRemarks4" ' +
		'		class="field border" style="width:69px;"/>';
	}else{
		td88.innerHTML = '';
	}
	tr.appendChild(td88);
	
	if (count == -99){
		
		td77 = document.createElement("td");
		td77.innerHTML = '';
		tr.appendChild(td77);
		
		td88 = document.createElement("td");
		td88.innerHTML = '<input type="text" ' +
							'		id="groupName' + costCount + '"' +
							'		name="costs[' + costCount +'].TCostGroup.CName" ' +
							'		class="field border" style="width:169px;" disabled="disabled" />';
		tr.appendChild(td88);
	}
	
	//增加行、删除行
	var td9 = document.createElement("td");
	td9.innerHTML = '<input type="button" value="ADD" style="float:left; margin-left:3px; width:40px; height:20px;"' + 
					' 		onclick = "addCost(' + costCount + ');" />' +
					'<input type="button" value="DEL" style="float:left; margin-left:5px; width:40px; height:20px;"' +
					'		onclick = "delCostInfo(' + costCount + ',this);" />';
	tr.appendChild(td9);
	
	if (count != -99){
		var k = -1;
		for(var i=0; i<rowcount; i++){
			if(tb.getElementsByTagName("tr")[i].id == "costTR"+count){
				k = i + 1;
				break;
			}
		}
		if (k == -1 || k == rowcount){
			document.getElementById("costTable").appendChild(tr);
		}else{
			var t = document.getElementById(tb.getElementsByTagName("tr")[k].id);
			t.parentNode.insertBefore(tr, t);
		}
	}else{
		document.getElementById("costTable").appendChild(tr);
	}
	
	
	getCostSelect(costCount);
	
	$('._select').comboSelect();
}

function computeTmpMoney(){
	var sumMoney = 0;
	var j = Number(costCount) + 1;
	for (var i=0; i<j; i++){
		if (document.getElementById("money_"+i) != null && document.getElementById("money_"+i).checked){
			sumMoney = Number(sumMoney) + Number(document.getElementById("CMoney"+i).value);
		}
	}
	costTmpSumMoney.innerHTML = "成本总计：" + sumMoney.toFixed(2);
}

/*删除成本*/
function delCostInfo(num, obj){
	if (confirm("确认删除？")){
		var costsId = document.getElementById("costsId"+num);
		if (costsId != null){
			var cid = document.getElementById("costsId"+num).value;
			if (cid != null && cid > 0){
				delCostIds[k] = cid;
				k++;
			}
		}
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
	}
}

/*增加回款*/
function addCash(rr){
	
	var tb = document.getElementById("cashTable");
	var rowcount = tb.rows.length;
	
	//回款数量
	cashCount = Number(cashCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "cashTR_" + cashCount.toString();
	
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TCashItem' + cashCount + '"' +  
					'		 name="cash[' + cashCount + '].TCostItem.CId"' +
					'		 class="border _select" style="width:426px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>' +
					'<input type="hidden" id="cashOrder1_' + cashCount + '"' + 
					'		 name="cash[' + cashCount + '].COrder"' + '/>';
	td2.setAttribute("width","430px");
	tr.appendChild(td2);
	
	//价格
	var td3 = document.createElement("td");
	if (rowcount == 0){
		td3.innerHTML = '<label>价格</label>';
	}else{
		td3.innerHTML = '';
	}
	td3.setAttribute("width","60px");
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CMoney2_' + cashCount + '" ' +
					'		name="cash[' + cashCount +'].CMoney" ' +
					'		class="field border" style="width:86px;"' +
					'		onchange="computeCash(' + cashCount + ')" />';
	td4.setAttribute("width","90px");
	tr.appendChild(td4);
	
	//数量
	var td5 = document.createElement("td");
	if (rowcount == 0){
		td5.innerHTML = '<label>数量</label>';
	}else{
		td5.innerHTML = '';
	}
	td5.setAttribute("width","60px");
	tr.appendChild(td5);
	
	var td6 = document.createElement("td");
	td6.innerHTML = '<input type="text" id="CastCount' + cashCount + '" ' +
					'		name="cash[' + cashCount +'].CCount" ' +
					'		class="field border" style="width:63px;"' +
					'		onchange="computeCash(' + cashCount + ')" />';
	td6.setAttribute("width","67px");
	tr.appendChild(td6);
	
	//金额
	var td10 = document.createElement("td");
	if (rowcount == 0){
		td10.innerHTML = '<label>金额</label>';
	}else{
		td10.innerHTML = '';
	}
	td10.setAttribute("width","60px");
	tr.appendChild(td10);
	
	var td11 = document.createElement("td");
	td11.innerHTML = '<input type="text" id="CashMoney' + cashCount + '" ' +
	 				 '		class="field border" style="width:86px;" readonly="readonly"/>'+
	 				 '<input type="hidden" id="CType' + cashCount + '" ' +
					 '			name="cash[' + cashCount +'].CType" ' +
					 '			value="1"/>';
	td11.setAttribute("width","90px");
	tr.appendChild(td11);
	
	//增加行、删除行
	var td9 = document.createElement("td");
	td9.setAttribute("width","143px");
	td9.innerHTML = '<input type="button" value="ADD" style="float:left; margin-left:8px; width:60px; height:20px;"' + 
					' 		onclick = "addCash(' + cashCount + ');" />' +
					'<input type="button" value="DELETE" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delCash(this)" />';
	tr.appendChild(td9);
	
	if (rr == -1){
		document.getElementById("cash").appendChild(tr);
	}else{
		var k = -1;
		for(var i=0; i<rowcount; i++){
			if(tb.getElementsByTagName("tr")[i].id == "cashTR_"+rr){
				k = i + 1;
				break;
			}
		}
		if (k == rowcount){
			document.getElementById("cash").appendChild(tr);
		}else{
			var t = document.getElementById(tb.getElementsByTagName("tr")[k].id);
			t.parentNode.insertBefore(tr, t);
		}
	}
	
	getCashSelect(cashCount);
	
	$('._select').comboSelect();
}

/*删除回款*/
function delCash(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
		setTitle(1);
	}
}

function setTitle(flag){
	var tbName = "";
	if (flag==1){
		tbName = "cashTable";
	}else if (flag==2){
		tbName = "cashTable2";
	}else if (flag==3){
		tbName = "rateTable";
	}else if (flag==4){
		tbName = "rateTable2";
	}
	var tb = document.getElementById(tbName);
	var rowcount = tb.rows.length;
	if (rowcount > 0){
		tb.rows[0].cells[1].innerHTML = "价格";
		tb.rows[0].cells[3].innerHTML = "数量";
		tb.rows[0].cells[5].innerHTML = "金额";
	}
}

/*增加回款2*/
function addCash2(rr){
	
	var tb = document.getElementById("cashTable2");
	var rowcount = tb.rows.length;
	
	//回款数量
	cash2Count = Number(cash2Count) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "cashTR2_" + cash2Count.toString();
	
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TCashItem2_' + cash2Count + '"' +  
					'		 name="cash2[' + cash2Count + '].TCostItem.CId"' +
					'		 class="border _select" style="width:426px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>' +
					'<input type="hidden" id="cashOrder' + cash2Count + '"' + 
					'		 name="cash2[' + cash2Count + '].COrder"' + '/>';
	td2.setAttribute("width","430px");
	tr.appendChild(td2);
	
	//价格
	var td3 = document.createElement("td");
	if (rowcount == 0){
		td3.innerHTML = '<label>价格</label>';
	}else{
		td3.innerHTML = '';
	}
	td3.setAttribute("width","60px");
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CMoney22_' + cash2Count + '" ' +
					'		name="cash2[' + cash2Count +'].CMoney" ' +
					'		class="field border" style="width:86px;"' +
					'		onchange="computeCash2(' + cash2Count + ')" />';
	td4.setAttribute("width","90px");
	tr.appendChild(td4);
	
	//数量
	var td5 = document.createElement("td");
	if (rowcount == 0){
		td5.innerHTML = '<label>数量</label>';
	}else{
		td5.innerHTML = '';
	}
	td5.setAttribute("width","60px");
	tr.appendChild(td5);
	
	var td6 = document.createElement("td");
	td6.innerHTML = '<input type="text" id="CastCount2_' + cash2Count + '" ' +
					'		name="cash2[' + cash2Count +'].CCount" ' +
					'		class="field border" style="width:63px;"' +
					'		onchange="computeCash2(' + cash2Count + ')" />';
	td6.setAttribute("width","67px");
	tr.appendChild(td6);
	
	//金额
	var td10 = document.createElement("td");
	if (rowcount == 0){
		td10.innerHTML = '<label>金额</label>';
	}else{
		td10.innerHTML = '';
	}
	td10.setAttribute("width","60px");
	tr.appendChild(td10);
	
	var td11 = document.createElement("td");
	td11.innerHTML = '<input type="text" id="CashMoney2_' + cash2Count + '" ' +
	 				 '		class="field border" style="width:86px;" readonly="readonly"/>'+
	 				'<input type="hidden" id="CType2_' + cash2Count + '" ' +
					 '			name="cash2[' + cash2Count +'].CType" ' +
					 '			value="2"/>';
	td11.setAttribute("width","90px");
	tr.appendChild(td11);
	
	//增加行、删除行
	var td9 = document.createElement("td");
	td9.setAttribute("width","143px");
	td9.innerHTML = '<input type="button" value="ADD" style="float:left; margin-left:8px; width:60px; height:20px;"' + 
					' 		onclick = "addCash2(' + cash2Count + ');" />' +
					'<input type="button" value="DELETE" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delCash2(this)" />';
	tr.appendChild(td9);
	
	if (rr == -1){
		document.getElementById("cash2").appendChild(tr);
	}else{
		var k = -1;
		for(var i=0; i<rowcount; i++){
			if(tb.getElementsByTagName("tr")[i].id == "cashTR2_"+rr){
				k = i + 1;
				break;
			}
		}
		if (k == rowcount){
			document.getElementById("cash2").appendChild(tr);
		}else{
			var t = document.getElementById(tb.getElementsByTagName("tr")[k].id);
			t.parentNode.insertBefore(tr, t);
		}
	}
	
	getCashSelect2(cash2Count);
	
	$('._select').comboSelect();
	
}

/*删除回款2*/
function delCash2(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
		setTitle(2);
	}
}


function addCostPrint(){
	for(var m=0; m<userCount; m++){
		var costTab = document.getElementById("costMoney"+m);
		var rows = costTab.rows.length;
		var k = 0;
		var sumMoney = 0;
		var description = "";
		for(var i=2; i<=rows; i++){
			var n = Number(i) - 1;
			var trid = costTab.rows[n].id;
			document.getElementById("xuh" + trid).value = Number(k+1);
			var costMoney = document.getElementById("money" + trid).value;
			if (costMoney == null){
				costMoney = 0;
			}
			//sumMoney += Number(costMoney.substring(1));
			sumMoney += Number(costMoney);
			var costitem = document.getElementById("costitem" + trid).value;
			if (costitem == null){
				costitem = "";
			}
			if (costitem != ""){
				description += costitem + "：";
			}
			if (costMoney != 0){
				description += Number(costMoney);
			}
			if (costitem != "" || costMoney != 0){
				description += "；";
			}
			k++;
		}
		if (rows < maxRows){
			var start = Number(rows);
			var end = maxRows;
			for(var i=start; i<end; i++){
				addPrint1(m, i, sumMoney);
			}
		}
		var _temp = Number(sumMoney).toFixed(2);
		document.getElementById("amount").value = _temp;
		document.getElementById("des").innerHTML = description;
		var tmp = Translate(parseInt(_temp).toString());
		var tmp2 = _temp.toString();
		var pos = tmp2.indexOf(".") + 1;
		if (pos <= 0){
			tmp2 = " Only";
		}else{
			tmp2 = " And Cents " + Translate(tmp2.substring(pos)) + " Only";
		}
		document.getElementById("amountInWord").value = tmp + tmp2;
	}
}

function addCashPrint(flag){
	
	//flag：1，2：打印不含税+含税
	//flag：3、4：不含税两个；5：含税1；6：含税2

	var costTab = document.getElementById("costMoney");
	var rows = costTab.rows.length;
	var k = 0;
	var start = 2;
	var sumMoney = 0;
	var rateMoney = 0;
	var sumRateMoney = 0;
	if (flag==2||flag==4||flag==5||flag==6){
		start = 3;
	}
	for(var i=start; i<=rows; i++){
		var n = Number(i) - 1;
		var trid = costTab.rows[n].id;
		document.getElementById("xuh" + trid).value = Number(k+1);
		var costMoney = document.getElementById("money" + trid).value;
		if (costMoney == null){
			costMoney = 0;
		}
		var costCount = document.getElementById("count" + trid).value;
		if (costCount == null){
			costCount = 0;
		}
		sumMoney = sumMoney + (Number(costMoney.substring(1)) * Number(costCount));
		var rate = 0;
		if (flag==2||flag==5||flag==6){
			rate = document.getElementById("rate" + trid).value;
			if (rate == null || rate == ""){
				continue;
			}
			rateMoney = rateMoney + (Number(costMoney.substring(1)) * Number(costCount) * Number(rate) / 100);
			sumRateMoney = sumRateMoney + (Number(costMoney.substring(1)) * Number(costCount) * (1 + Number(rate) / 100));
		}
		
		k++;
	}
	if (rows < cashMaxRows){
		var start = 0;
		if (flag==1||flag==3||flag==4){
			start = Number(rows);
		}else{
			start = Number(rows) - 1;
		}
		var end = cashMaxRows;
		for(var i=start; i<end; i++){
			if (flag==1||flag==3||flag==4){
				addPrint2(i, sumMoney);
			}else if(flag==2||flag==6){
				addPrint3(i, sumRateMoney);
			}else if(flag==5){
				addPrint4(i, sumRateMoney);
			}
		}
	}else{
		if (flag==1 || flag==3 || flag==4){
			document.getElementById("total").innerHTML = thousandBitSeparator(Number(sumMoney).toFixed(2));
		}
	}
	if (flag==2||flag==5||flag==6){
		document.getElementById("amount").innerHTML = thousandBitSeparator(sumMoney.toFixed(2));
		document.getElementById("vat").innerHTML = thousandBitSeparator(rateMoney.toFixed(2));
		document.getElementById("total").innerHTML = thousandBitSeparator(Number(sumMoney + rateMoney).toFixed(2));
	}
	
	var enSumMoney = 0;
	if (flag==2||flag==5||flag==6){
		enSumMoney = sumRateMoney;
	}
	
	////打印余额
	if (flag==3||flag==4||flag==5||flag==6){
		var CRecieveMoney = document.getElementById("CRecieveMoney").value;
		if (CRecieveMoney == null || CRecieveMoney == ""){
			CRecieveMoney = 0;
		}alert
		var dueMoney = Number(sumMoney + rateMoney - CRecieveMoney).toFixed(2);
		document.getElementById("due").innerHTML = '$' + thousandBitSeparator(dueMoney);
		enSumMoney = dueMoney;
		
		//add by 20190524 柬币
		if (document.getElementById("_businRate") != null){
			var _businRate = document.getElementById("_businRate").value;
			if (_businRate != null || _businRate != "" && !isNaN(_businRate)){
				document.getElementById("amountKHR").innerHTML = thousandBitSeparator(Number(document.getElementById("amount").innerHTML.replace(RegExp(",", "g"), "")*_businRate).toFixed(0));
				document.getElementById("vatKHR").innerHTML = thousandBitSeparator(Number(document.getElementById("vat").innerHTML.replace(RegExp(",", "g"), "")*_businRate).toFixed(0));
				document.getElementById("totalKHR").innerHTML = thousandBitSeparator(Number(document.getElementById("total").innerHTML.replace(RegExp(",", "g"), "")*_businRate).toFixed(0));
			}
		}
	}
	var tmp = Translate(parseInt(enSumMoney).toString());
	var tmp2 = enSumMoney.toString();
	var pos = tmp2.indexOf(".") + 1;
	if (pos > 0){
		var tmp3 = tmp2.substring(pos);
		if (parseInt(tmp3) > 0){
			tmp2 = " And Cents " + Translate(tmp3) + " Only";
		}else{
			tmp2 = " Only";
		}
	}else{
		tmp2 = " Only";
	}
	if (sumMoney > 0){
		document.getElementById("totalDollars").innerHTML = 'Say Total US. Dollar<label style="margin-left:10px;"></label>' +  tmp + tmp2;
	}else{
		document.getElementById("totalDollars").innerHTML = 'Say Total US. Dollar';
	}
}


function addPrint1(userCount, costFlag, totals){
	
	var tr = document.createElement("tr");
	
	if (costFlag < Number(maxRows) - 1){
		
		var td = document.createElement("td");
		td.innerHTML = '<input style="border:0px;width:38px;text-align:center;margin-top:6px;" value="' + Number(costFlag) + '" readonly="readonly"/>';
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
	}else{
		
		var td = document.createElement("td");
		td.innerHTML = '';
		td.setAttribute("colspan", 3);
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = '<div style="font-weight:bold;margin-top:6px;">TOTAL:<div>';
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = '<div style="font-weight:bold;margin-top:6px;text-align:center;">$' + thousandBitSeparator(Number(totals).toFixed(2)) + '</div>';
		tr.appendChild(td);
	}
	
	document.getElementById("costMoney"+userCount).appendChild(tr);
	
}

function addPrint2(costFlag, totals){
	
	var tr = document.createElement("tr");
	
	if (costFlag < Number(cashMaxRows) - 1){
		
		var td = document.createElement("td");
		td.innerHTML = '';
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = "";
		tr.appendChild(td);
		
	}else{
		/*
		var td = document.createElement("td");
		td.innerHTML = '';
		td.setAttribute("colspan", 3);
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = '<div style="font-weight:bold;margin-top:6px;border:0px;">TOTAL:<div>';
		tr.appendChild(td);
		
		td = document.createElement("td");
		td.innerHTML = '<div style="font-weight:bold;margin-top:6px;text-align:center;">$' + Number(totals).toFixed(2) + '</div>';
		tr.appendChild(td);*/
		document.getElementById("total").innerHTML = thousandBitSeparator(Number(totals).toFixed(2));
	}
	
	document.getElementById("costMoney").appendChild(tr);
	
}

function addPrint3(costFlag, totals){
	
	var tr = document.createElement("tr");
	
	var td = document.createElement("td");
	td.innerHTML = '';
	tr.appendChild(td);
	//td.setAttribute("border-top","0px;");
	//td.setAttribute("border-bottom","0px;");
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	document.getElementById("costMoney").appendChild(tr);
	
}

function addPrint4(costFlag, totals){
	
	var tr = document.createElement("tr");
	
	var td = document.createElement("td");
	td.innerHTML = '';
	tr.appendChild(td);
	//td.setAttribute("border-top","0px;");
	//td.setAttribute("border-bottom","0px;");
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "";
	tr.appendChild(td);
	
	document.getElementById("costMoney").appendChild(tr);
	
}

function addRate(){
	
	var tb = document.getElementById("rateTable");
	var rowcount = tb.rows.length;
	
	//回款数量
	rateCount = Number(rateCount) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "rateTR" + serviceCount.toString();
	
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TRateItem' + rateCount + '"' +  
					'		 name="rate[' + rateCount + '].TCostItem.CId"' +
					'		 class="border" style="width:293px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>';
	td2.setAttribute("width","297px");
	tr.appendChild(td2);
	
	//价格
	var td3 = document.createElement("td");
	if (rowcount == 0){
		td3.innerHTML = '<label>价格</label>';
	}else{
		td3.innerHTML = '';
	}
	td3.setAttribute("width","30px");
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CMoney3' + rateCount + '" ' +
					'		name="rate[' + rateCount +'].CMoney" ' +
					'		class="field border" style="width:86px;"' +
					'	onchange="computeRate(' + rateCount + ')" />';
	td4.setAttribute("width","90px");
	tr.appendChild(td4);
	
	//数量
	var td33 = document.createElement("td");
	if (rowcount == 0){
		td33.innerHTML = '<label>数量</label>';
	}else{
		td33.innerHTML = '';
	}
	td33.setAttribute("width","30px");
	tr.appendChild(td33);
	
	var td44 = document.createElement("td");
	td44.innerHTML = '<input type="text" id="RateCount' + rateCount + '" ' +
					'		name="rate[' + rateCount +'].CCount" ' +
					'		class="field border" style="width:36px;"' +
					'	onchange="computeRate(' + rateCount + ')" />';
	td44.setAttribute("width","40px");
	tr.appendChild(td44);
	
	//金额
	var td10 = document.createElement("td");
	if (rowcount == 0){
		td10.innerHTML = '<label>金额</label>';
	}else{
		td10.innerHTML = '';
	}
	td10.setAttribute("width","30px");
	tr.appendChild(td10);
	
	var td11 = document.createElement("td");
	td11.innerHTML = '<input type="text" id="CMoney6' + rateCount + '" ' +
					 '		class="field border" style="width:86px;" readonly="readonly"/>'
	td11.setAttribute("width","90px");
	tr.appendChild(td11);
	
	//税率
	var td5 = document.createElement("td");
	td5.setAttribute("width","60px");
	td5.innerHTML = '<select id="Rate' + rateCount + '"' +  
					'		 name="rate[' + rateCount + '].TRate.CId"' +
					'		 class="border" style="width:56px;"' +
					'	onchange="computeRate(' + rateCount + ')">' +
					'</select>';
	tr.appendChild(td5);
	
	//选择费率后金额
	var td6 = document.createElement("td");
	td6.setAttribute("width","183px");
	td6.innerHTML = '<input type="text" id="CMoney5' + rateCount + '" ' +
					'		class="field border" style="width:50px;" readonly="readonly"/>' +
					'<input type="text" id="CMoney4' + rateCount + '" ' +
					'		class="field border" style="width:116px;margin-left:5px;" readonly="readonly"/>';
	tr.appendChild(td6);
	
	var td9 = document.createElement("td");
	td9.setAttribute("width","144px");
	td9.innerHTML = '<input type="button" value="ADD" style="float:left; margin-left:8px; width:60px; height:20px;"' + 
					' 		onclick = "addRate();" />' +
					'<input type="button" value="DELETE" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delRate(this)" />'+
					'<input type="hidden" id="CType' + rateCount + '" ' +
					 '			name="rate[' + rateCount +'].CType" ' +
					 '			value="1" />';
	tr.appendChild(td9);
	
	document.getElementById("rate").appendChild(tr);
	
	getRateSelect(rateCount);
}

/*删除回款*/
function delRate(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
		setTitle(3);
	}
}

function addRate2(rr){
	
	var tb = document.getElementById("rateTable2");
	var rowcount = tb.rows.length;
	
	//回款数量
	rate2Count = Number(rate2Count) + 1;
	
	//创建行
	var tr = document.createElement("tr");
	tr.id = "rateTR2_" + rate2Count.toString();
	
	var td2 = document.createElement("td");
	td2.innerHTML = '<select id="TRateItem2_' + rate2Count + '"' +  
					'		 name="rate2[' + rate2Count + '].TCostItem.CId"' +
					'		 class="border _select" style="width:293px;">' +
					'	<option value="0">PLEASE CHOOSE</option>' +
					'</select>' +
					'<input type="hidden" id="rateOrder' + rate2Count + '"' + 
					'		 name="rate2[' + rate2Count + '].COrder"' + '/>';
	td2.setAttribute("width","297px");
	tr.appendChild(td2);
	
	//价格
	var td3 = document.createElement("td");
	if (rowcount == 0){
		td3.innerHTML = '<label>价格</label>';
	}else{
		td3.innerHTML = '';
	}
	td3.setAttribute("width","30px");
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	td4.innerHTML = '<input type="text" id="CMoney32_' + rate2Count + '" ' +
					'		name="rate2[' + rate2Count +'].CMoney" ' +
					'		class="field border" style="width:86px;"' +
					'	onchange="computeRate2(' + rate2Count + ')" />';
	td4.setAttribute("width","90px");
	tr.appendChild(td4);
	
	//数量
	var td33 = document.createElement("td");
	if (rowcount == 0){
		td33.innerHTML = '<label>数量</label>';
	}else{
		td33.innerHTML = '';
	}
	td33.setAttribute("width","30px");
	tr.appendChild(td33);
	
	var td44 = document.createElement("td");
	td44.innerHTML = '<input type="text" id="RateCount2_' + rate2Count + '" ' +
					'		name="rate2[' + rate2Count +'].CCount" ' +
					'		class="field border" style="width:36px;"' +
					'	onchange="computeRate2(' + rate2Count + ')" />';
	td44.setAttribute("width","40px");
	tr.appendChild(td44);
	
	//金额
	var td10 = document.createElement("td");
	if (rowcount == 0){
		td10.innerHTML = '<label>金额</label>';
	}else{
		td10.innerHTML = '';
	}
	td10.setAttribute("width","30px");
	tr.appendChild(td10);
	
	var td11 = document.createElement("td");
	td11.innerHTML = '<input type="text" id="CMoney62_' + rate2Count + '" ' +
					 '		class="field border" style="width:86px;" readonly="readonly"/>'
	td11.setAttribute("width","90px");
	tr.appendChild(td11);
	
	//税率
	var td5 = document.createElement("td");
	td5.setAttribute("width","60px");
	td5.innerHTML = '<select id="Rate2_' + rate2Count + '"' +  
					'		 name="rate2[' + rate2Count + '].TRate.CId"' +
					'		 class="border" style="width:56px;"' +
					'	onchange="computeRate2(' + rate2Count + ')">' +
					'</select>';
	tr.appendChild(td5);
	
	//选择费率后金额
	var td6 = document.createElement("td");
	td6.setAttribute("width","183px");
	td6.innerHTML = '<input type="text" id="CMoney52_' + rate2Count + '" ' +
					'		class="field border" style="width:50px;" readonly="readonly"/>' +
					'<input type="text" id="CMoney42_' + rate2Count + '" ' +
					'		class="field border" style="width:116px;margin-left:5px;" readonly="readonly"/>';
	tr.appendChild(td6);
	
	var td9 = document.createElement("td");
	td9.setAttribute("width","144px");
	td9.innerHTML = '<input type="button" value="ADD" style="float:left; margin-left:8px; width:60px; height:20px;"' + 
					' 		onclick = "addRate2(' + rate2Count + ');" />' +
					'<input type="button" value="DELETE" style="float:left; margin-left:10px; width:60px; height:20px;"' +
					'		onclick = "delRate2(this)" />'+
					'<input type="hidden" id="CType2' + rate2Count + '" ' +
					 '			name="rate2[' + rate2Count +'].CType" ' +
					 '			value="2"/>';
	tr.appendChild(td9);
	
	if (rr == -1){
		document.getElementById("rate2992").appendChild(tr);
	}else{
		var k = -1;
		for(var i=0; i<rowcount; i++){
			if(tb.getElementsByTagName("tr")[i].id == "rateTR2_"+rr){
				k = i + 1;
				break;
			}
		}
		if (k == rowcount){
			document.getElementById("rate2992").appendChild(tr);
		}else{
			var t = document.getElementById(tb.getElementsByTagName("tr")[k].id);
			t.parentNode.insertBefore(tr, t);
		}
	}
		
	getRateSelect2(rate2Count);
	
	$('._select').comboSelect();
	
}

/*删除回款*/
function delRate2(obj){
	if (confirm("确认删除？")){		
		var tr = obj.parentNode.parentNode;
		var tbody = tr.parentNode;
		tbody.removeChild(tr);
		setTitle(4);
	}
}