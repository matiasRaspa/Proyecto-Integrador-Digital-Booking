import React, { useEffect, useState } from "react";
import { BarComeHome } from "../bar-come-home/BarComeHome";
import { useFetch } from "../../hooks/useFetch";
import { getIcons } from "../../helper/getIcons";
import ModalPortal from "../modals/ModalPortal";
import { SuccessAdmin } from "../modals/success/SuccessAdmin";
import { LoadImages } from "./loadImages/LoadImages";
import GooglePlacesAutocomplete from "react-google-places-autocomplete";
import { geocodeByAddress, getLatLng } from "react-google-places-autocomplete";
//import axios
import axios from "axios";

export const CreateProduct = () => {
  let GOOGLE_API_KEY = "AIzaSyC_Y7GbqIDYEz_m0mO1Uq0XpFikt2wOYq4";

  const title = "Administración";

  const [lugar, setLugar] = useState(null);
  const [latitud, setLatitud] = useState(null);
  const [longitud, setLongitud] = useState(null);
  const [nombrePropiedad, setNombrePropiedad] = useState("");
  const [nombreCategoria, setNombreCategoria] = useState("");
  const [nombreCiudad, setNombreCiudad] = useState("");
  const [descripcionLugar, setDescripcionLugar] = useState("");
  const [atributos, setAtributos] = useState([]);
  const [politicas, setPoliticas] = useState([]);
  const [imagenes, setImagenes] = useState();
  const [success, setSuccess] = useState(false);

  console.log(imagenes);

  const [categories] = useFetch(
    "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/categories"
  );

  const [cities] = useFetch(
    "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/cities"
  );

  const [features] = useFetch(
    "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/features"
  );

  const [politics] = useFetch(
    "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/politics"
  );

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  useEffect(() => {
    if (lugar !== null) {
      geocodeByAddress(lugar.label)
        .then((results) => getLatLng(results[0]))
        .then(({ lat, lng }) => {
          setLatitud(lat);
          setLongitud(lng);
        });
    }
  }, [lugar]);

  function handleChangeNombrePropiedad(e) {
    const { value } = e.target;
    setNombrePropiedad(value);
  }

  function handleChangeNombreCategoria(e) {
    const { value } = e.target;
    setNombreCategoria(value);
  }

  function handleChangeNombreCiudad(e) {
    const { value } = e.target;
    setNombreCiudad(value);
  }

  function handleChangeDescripcionLugar(e) {
    const { value } = e.target;
    setDescripcionLugar(value);
  }

  function handleChangeAtributos(e, atributo) {
    const { checked } = e.target;

    if (checked) {
      setAtributos([...atributos, { id: parseInt(atributo.id) }]);
    } else {
      const isInTheArray = atributos.some((at) => at === atributo.id);
      if (isInTheArray) {
        setAtributos(atributos.filter((at) => at !== atributo.id));
      }
    }
  }

  function handleChangePoliticas(e, politica) {
    const { checked } = e.target;

    if (checked) {
      setPoliticas([...politicas, { id: parseInt(politica.id) }]);
    } else {
      const isInTheArray = politicas.some((po) => po === politica.id);
      if (isInTheArray) {
        setPoliticas(politicas.filter((po) => po !== politica.id));
      }
    }
  }

  function handleFetchLoadImages(id, images) {
    /* let formdata = new FormData();
    formdata.append('file',images[0],'img 01.jpg');
    console.log(formdata);
    // for (let i = 0; i < photos.files.length; i++) {
    //   formData.append(`photos_${i}`, photos.files[i]);
    // }

    let requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "multipart/form-data ",
      },
      body: formdata,
      redirect: 'follow'
    };

    fetch(
      "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/imageFile/up?" + new URLSearchParams({ productId: id }),

      requestOptions
    )
      .then((res) => {
        console.log(res.json);
        console.log(res.status);
        if (res.status === 201) {
          setSuccess(true);
        }
      })
      .catch((err) => console.log(err)); */


      let formdata = new FormData();
      for (let i = 0; i < images.length; i++) {
        formdata.append(`files`, images[i]);
       
      }
    axios({
      url: "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/imageFile/upAll?" +
          new URLSearchParams({ productId: id }),
      method: "POST",
      headers: {
        "Content-Type": "multipart/form-data ",
      },
      data: formdata,
      redirect: 'follow'
    })
      .then((res) => {
        console.log(res.json);
        console.log(res.status);
        if (res.status === 201) {
          setSuccess(true);
        }
      }
      )
      .catch((err) => console.log(err));

  }

  const handleSubmit = (e) => {
    e.preventDefault();
    let data = {
      name: nombrePropiedad,
      description: descripcionLugar,
      category: { id: parseInt(nombreCategoria) },
      location: { id: parseInt(nombreCiudad) },
      features: atributos,
      politics: politicas,
      score: 10,
      latitude: latitud,
      longitude: longitud,
    };
    console.log(data);

    let requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };

    fetch(
      "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products",
      requestOptions
    )
      .then((response) => {
        console.log(response);
        if (response.status === 201) {
          return response.json();
        }
      })
      .then((data) => {
        console.log(data.id);
        handleFetchLoadImages(data.id, imagenes);
      })

      // .then((res) => {
      //   console.log(res.json().id);
      //   if (res.status === 201) {
      //     handleFetchLoadImages(imagenes);
      //   }
      // })
      // .then ({

      //   if (res.status === 201) {
      //     handleFetchLoadImages(imagenes);
      //   }
      // })
      .catch((err) => console.log(err));
  };

  return (
    <>
      <ModalPortal>
        <SuccessAdmin state={success} />
      </ModalPortal>

      <BarComeHome title={title} />

      <section>
        <h2 className="create_product_h2">Crear propiedad</h2>
        <div className="create_product_div_containter_all">
          <form
            onSubmit={(e) => {
              handleSubmit(e);
            }}
            method="POST"
            name="createProduct"
            id="formularioCreateProduct"
            className="create_product_div_containter_form"
          >
            <section>
              <div className="create_product_div_datos_producto">
                <label className="booking_datos_usuario_label">
                  Nombre de la propiedad
                  <input
                    name="nombre"
                    className="create_product_input"
                    type="text"
                    id="nombre"
                    placeholder="Nombre de la propiedad"
                    onChange={handleChangeNombrePropiedad}
                    value={nombrePropiedad}
                    required
                  />
                </label>
                <label className="booking_datos_usuario_label">
                  Categoria
                  <select
                    id="categoria"
                    name="categoria"
                    className="create_product_input"
                    onChange={handleChangeNombreCategoria}
                    value={nombreCategoria}
                    required
                  >
                    <option hidden value="">
                      Seleccionar la categoría
                    </option>
                    {categories &&
                      categories.map((category) => (
                        <option value={category.id} key={category.title}>
                          {category.title}
                        </option>
                      ))}
                  </select>
                </label>
                <label className="booking_datos_usuario_label">
                  Dirección
                  <GooglePlacesAutocomplete
                    selectProps={{ lugar, onChange: setLugar }}
                    apiKey={GOOGLE_API_KEY}
                    minLengthAutocomplete={5}
                  />
                </label>
                <label className="booking_datos_usuario_label">
                  Ciudad
                  <select
                    id="ciudad"
                    name="ciudad"
                    placeholder="Ciudad"
                    className="create_product_input"
                    onChange={handleChangeNombreCiudad}
                    value={nombreCiudad}
                    required
                  >
                    <option hidden value="">
                      Seleccionar la ciudad
                    </option>
                    {cities &&
                      cities.map((city) => (
                        <option value={city.id} key={city.name}>
                          {city.name}
                        </option>
                      ))}
                  </select>
                </label>
              </div>
              <label className="booking_datos_usuario_label">
                Descripción
                <textarea
                  name="descripcion"
                  placeholder="Escribir aquí"
                  className="create_product_input_description"
                  onChange={handleChangeDescripcionLugar}
                  value={descripcionLugar}
                  required
                />
              </label>
            </section>
            <hr className="create_product_hr" />
            <section>
              <h3 className="create_product_h3">Agregar atributos</h3>
              <div className="create_product_div_container_datos_producto">
                <ul className="create_product_div_features_producto">
                  {features &&
                    features.map((atributo) => (
                      <li key={atributo.id}>
                        <label
                          htmlFor={atributo.id}
                          className="create_product_label_datos_producto"
                        >
                          <input
                            type="checkbox"
                            id="atributo"
                            name="atributo"
                            className="create_product_input_datos_producto"
                            value={atributo.id}
                            onChange={(e) => handleChangeAtributos(e, atributo)}
                          />
                          <div>
                            {getIcons(atributo, "product__icon")} {atributo.title}
                          </div>
                        </label>
                      </li>
                    ))}
                </ul>
              </div>
            </section>
            <hr className="create_product_hr" />
            <section>
              <h3 className="create_product_h3">Políticas del producto</h3>
              <section>
                <div className="create_product_section_politicas_producto">
                  {politics &&
                    politics
                      .filter((e) => e.id < 7)
                      .map((politica) => (
                        <div key={politica.id}>
                          <label
                            htmlFor={politica.id}
                            className="booking_datos_usuario_label"
                          >
                            <div className="create_product_div_container_politicas_producto">
                              <div className="create_product_div_container_check_politicas">
                                <input
                                  type="checkbox"
                                  id="politicas"
                                  name="politicas"
                                  value={politicas}
                                  className="create_product_input_politicas_producto"
                                  onChange={(e) => handleChangePoliticas(e, politica)}
                                />
                                <h4 className="create_product_h4_politicas">
                                  {politica.title}
                                </h4>
                              </div>
                              {politica.description}
                            </div>
                          </label>
                        </div>
                      ))}
                </div>
              </section>
            </section>
            <hr className="create_product_hr" />
            <section>
              <h3 className="create_product_h3">Cargar imágenes</h3>

              <LoadImages setImages={setImagenes} />
            </section>
            <div className="booking_div_btn_container">
              <button type="submit" className="product_btn_form">
                Crear
              </button>
            </div>
          </form>
        </div>
      </section>
    </>
  );
};
