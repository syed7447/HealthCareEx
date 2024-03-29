 function uploadFile() {
        var file=document.getElementById("fileOb");
        var form=new FormData();
        form.append("image",file.files[0]);
        var inputs={
            url:'https://api.imgbb.com/1/upload?key=56a2b12e61d36a1d8b573da73680a8ea',
            method:"POST",
            timeout:0,
            processData:false,
            mimeType:"multipart/form-data",
            contentType:false,
            data:form
        };
        $.ajax(inputs).done(function (response){
            var job=JSON.parse(response);
            $("#imgLoc").val(job.data.url);

        })
      }