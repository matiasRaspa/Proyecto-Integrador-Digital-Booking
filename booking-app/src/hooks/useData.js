import { useState, useEffect } from 'react'

export const useData = (data) => {
    const [name, setName] = useState(null);
    const [latitude, setLatitude] = useState(null);
    const [longitude, setLongitude] = useState(null);

    useEffect(() => {
        if(data != null){
            setName(data.name)
            setLatitude(data.latitude)
            setLongitude(data.longitude)
          }
    }, [data])

  return (
    [name, latitude, longitude]
  )
}
