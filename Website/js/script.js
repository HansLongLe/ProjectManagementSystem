/*globals DOMParser: false*/
var text = "<ProjectManagementSystem><projects><estimatedTime>12</estimatedTime><requirements><estimatedTime>12</estimatedTime><name>reqA</name><description>req</description><ID>1234</ID><priority>1</priority><deadline><month>12</month><year>2020</year><day>12</day></deadline><hoursWorked>0</hoursWorked><tasks><estimatedTime>12</estimatedTime><name>task</name><description>task</description><ID>1234</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><employee><firstName>Jan</firstName><lastName>Le</lastName><employeeID>304287</employeeID><position>ScrumMaster</position><salary>35000.0</salary></employee><hoursWorked>1</hoursWorked><status>Not started</status></tasks><status>Not started</status></requirements><name>project1</name><description>project</description><ID>9998</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><productOwner><firstName>andrei</firstName><lastName>soldan</lastName></productOwner><hoursWorked>2</hoursWorked><status>Not started</status></projects><projects><estimatedTime>12</estimatedTime><requirements><estimatedTime>12</estimatedTime><name>requirement</name><description>req2</description><ID>1233</ID><priority>1</priority><deadline><month>12</month><year>2020</year><day>12</day></deadline><hoursWorked>0</hoursWorked><tasks><estimatedTime>12</estimatedTime><name>task</name><description>task2</description><ID>1233</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><employee><firstName>Martin</firstName><lastName>Vosta</lastName><employeeID>304372</employeeID><position>ProjectCreator</position><salary>32000.0</salary></employee><hoursWorked>12</hoursWorked><status>Not started</status></tasks><status>Not started</status></requirements><name>project2</name><description>project2</description><ID>1233</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><productOwner><firstName>iana</firstName><lastName>postolachi</lastName></productOwner><hoursWorked>0</hoursWorked><status>Not started</status></projects><projects><estimatedTime>12</estimatedTime><name>lalala</name><description>dsdsd</description><ID>1111</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><productOwner><firstName>andrei</firstName><lastName>dumitru</lastName></productOwner><hoursWorked>0</hoursWorked><status>Not started</status></projects><projects><estimatedTime>12</estimatedTime><name>lalala</name><description>dsdsd</description><ID>1111</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><productOwner><firstName>andrei</firstName><lastName>dumitru</lastName></productOwner><hoursWorked>0</hoursWorked><status>Not started</status></projects><projects><estimatedTime>12</estimatedTime><requirements><estimatedTime>123</estimatedTime><name>asd</name><description>asdf</description><ID>1234</ID><priority>2</priority><deadline><month>12</month><year>2312</year><day>12</day></deadline><hoursWorked>0</hoursWorked><tasks><estimatedTime>12</estimatedTime><name>task</name><description>asdfg</description><ID>1234</ID><deadline><month>11</month><year>2222</year><day>1</day></deadline><employee><firstName>Jan</firstName><lastName>Le</lastName><employeeID>304287</employeeID><position>ScrumMaster</position><salary>35000.0</salary></employee><hoursWorked>123</hoursWorked><status>Not started</status></tasks><tasks><estimatedTime>12</estimatedTime><name>task2</name><description>asdfgh</description><ID>1212</ID><deadline><month>12</month><year>2111</year><day>12</day></deadline><employee><firstName>Jan</firstName><lastName>Le</lastName><employeeID>304287</employeeID><position>ScrumMaster</position><salary>35000.0</salary></employee><hoursWorked>12</hoursWorked><status>Not started</status></tasks><status>Not started</status></requirements><name>projectFinal</name><description>asasas</description><ID>4545</ID><deadline><month>12</month><year>2020</year><day>18</day></deadline><productOwner><firstName>martin</firstName><lastName>vosta</lastName></productOwner><hoursWorked>270</hoursWorked><status>Started</status></projects><projects><estimatedTime>12</estimatedTime><requirements><estimatedTime>12</estimatedTime><name>asas</name><description>dsdsd</description><ID>1234</ID><priority>1</priority><deadline><month>12</month><year>2020</year><day>12</day></deadline><hoursWorked>0</hoursWorked><tasks><estimatedTime>12</estimatedTime><name>task</name><description>LALAL</description><ID>1234</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><employee><firstName>Jan</firstName><lastName>Le</lastName><employeeID>304287</employeeID><position>ScrumMaster</position><salary>35000.0</salary></employee><hoursWorked>12</hoursWorked><status>Not started</status></tasks><status>Not started</status></requirements><name>project10</name><description>lalala</description><ID>9999</ID><deadline><month>12</month><year>2020</year><day>12</day></deadline><productOwner><firstName>iana</firstName><lastName>p</lastName></productOwner><hoursWorked>0</hoursWorked><status>Not started</status></projects><projects><estimatedTime>12</estimatedTime><requirements><estimatedTime>12</estimatedTime><name>requirement1</name><description>requiremeny 1</description><ID>1234</ID><priority>1</priority><deadline><month>12</month><year>2020</year><day>18</day></deadline><hoursWorked>0</hoursWorked><tasks><estimatedTime>12</estimatedTime><name>task1</name><description>task1</description><ID>1234</ID><deadline><month>12</month><year>2020</year><day>18</day></deadline><employee><firstName>Jan</firstName><lastName>Le</lastName><employeeID>304287</employeeID><position>ScrumMaster</position><salary>35000.0</salary></employee><hoursWorked>0</hoursWorked><status>Not started</status></tasks><status>Approved</status></requirements><name>ProjectCheckForXML</name><description>This is the project that must appear in xml) and now I would like to see if changing works too</description><ID>2001</ID><deadline><month>12</month><year>2020</year><day>18</day></deadline><productOwner><firstName>Martin</firstName><lastName>Vosta</lastName></productOwner><hoursWorked>0</hoursWorked><status>Not started</status></projects></ProjectManagementSystem>";
//var parser, xmlDoc, document;
//
//function displayContent(n) {
//    parser = new DOMParser();
//    xmlDoc = parser.parseFromString(text, "text/xml");
//    var x = xmlDoc.getElementsByTagName("name");
//    return x[n].childNodes[2].nodeValue;
//
//}
//var trs = doucment.getElementsByTagName("tr");
//var tds = document.getElementsByTagName("td");
//trs[2].get(tds[2].innerHTML) = displayContent(0);


function readXML() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            showData(this);
        }
    };
    xmlhttp.open("GET", "xml/PMS.xml", true);
    xmlhttp.send();
}

function showData(xml) {
    var i;
    var xmlDoc = xml.responseXML;
    var table = "<tr><th>Project</th><th>ID</th><th>Estimated Time</th></tr>";
    var x = xmlDoc.getElementsByTagName("projects");
    for (i = 0; i < x.length; i++) {
        tabel += "<tr><td>" +
            x[i].getElementsByTagName("name")[2].childNodes[0].nodeValue + "</td><td>" +
            x[i].getElementsByTagName("ID")[2].childNodes[0].nodeValue + "</td><td>" +
            x[i].getElementsByTagName("estimatedTime")[1].childNodes[0].nodeValue + "</td></tr>";

    }
    document.getElementById("tab1").innerHTML = table;
}

//function displayProjects() {
//    parser = new DOMParser();
//    xmlDoc = parser.parseFrpmString(text, "text/xml");
//    var x = xmlDoc.getElementsByTagName("projects");
//    var listLength = x.length;
//
//    var table = "<table><tr><th>Project</th><th>ID</th></tr>";
//    for (var i = 0; i < listLength; i++) {
//        table += "<tr><td>" + x[i].getElementsByTagName("name")[2].childNodes[0].nodeValue + "</td>" +
//            "<td>" = x[i].getElementsByTagName("ID")[2].childNodes[0].nodevalue + "</td>"
//        "</tr>";
//    }
//    table += "</table>";
//    document.getElementById("tab1").innerHTML = table;
//}
//
readXML();
