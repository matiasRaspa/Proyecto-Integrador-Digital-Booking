import React from 'react'

const Map = ({ name, latitude, longitude, height = "450px" }) => {

  let newName = '';
  let fullName = '';
  if (name){
    if (name.includes('&')) {
      newName = name.replace(/[&]+/g, '%26');
      fullName = newName.replace(/\s+/g, '+');
    } else {
      fullName = name.replace(/\s+/g, '+');
    }
  }
  
  const url = `https://www.google.com/maps/embed/v1/place?key=AIzaSyDqGQhTTknu4Bfw1R1gXgoSXoYiYejzA-M&q=${fullName}&center=${latitude},${longitude}&zoom=16`
 
  return (
    <iframe
      title={name}
      width="100%"
      height={height}
      referrerPolicy="no-referrer-when-downgrade"
      src={url}
      allowFullScreen >
    </iframe>
  )
}

export default Map;
