import { useEffect, useState } from "react";


export const useFetch = (url) => {

    const [data, setData] = useState(null);

    useEffect(() => {

        fetch(url)
            .then(resp => {
                if (resp.status !== 200) {
                    setData('No se encontraron resultados');
                } else {
                    resp.json()
                        .then(data => {
                            setData(data)
                        })
                }
            }
            )
            .catch(err => console.log(err))

    }, [url])



    return [data];
}


