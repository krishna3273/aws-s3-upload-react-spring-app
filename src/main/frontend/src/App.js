import React ,{useState,useEffect,useCallback} from "react";
import {useDropzone} from 'react-dropzone'
import axios from "axios";
import './App.css'


function Dropzone({userId}) {
  const onDrop = useCallback(acceptedFiles => {
    let file=acceptedFiles[0];
    console.log(file)
    const formData=new FormData()
    formData.append("file",file)

    axios.post(`http://localhost:8080/api/user-profile/${userId}/image/upload`,formData,
    {
      headers:{
        "Content-Type":"multipart/form-data"
      }
    }).then(()=>{
      console.log("Profile Image uploaded successfully")
    }).catch(err=>console.log(err))
  }, [userId])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag 'n' drop profile image, or click to select profile image</p>
      }
    </div>
  )
}

const UserProfiles=()=>{

  const [userProfiles,setUserProfiles]=useState([])
  const fetchUserProfiles=async ()=>{
    let res=await axios.get("http://localhost:8080/api/user-profile")
    setUserProfiles(res.data)
  }
  useEffect(()=>{
    fetchUserProfiles()
  },[])
  return userProfiles.map((userProfile,index)=>{
    return (
      <div key={index}>
        {userProfile.userImageLink ? <img alt={userProfile.username} src={`http://localhost:8080/api/user-profile/${userProfile.userId}/image/download`}/>:null}
        <br/>
        <br/>
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userId}</p>
        <Dropzone {...userProfile}></Dropzone>
        <br/>
      </div>
    )
  })
}



function App() {
  return (
    <div className="App">
      <UserProfiles></UserProfiles>
    </div>
  );
}

export default App;
