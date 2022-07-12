import React, { useEffect, useState } from "react";
import { PreviewImages } from "./PreviewImages";
import uploadImage from "../../../img/upload-image.svg";

export const LoadImages = ({ setImages }) => {
  //aca estan las imagenes que se subieron en tipo file
  const [selectedImages, setSelectedImages] = useState([]);
  const [errorImages, setErrorImages] = useState(false);
  // aca estan las imagenes que se subieron en tipo url
  const [urlsSelectedImages, setUrlsSelectedImages] = useState([]);

  const onSelectFile = (event) => {
    if (event.target.files.length > 10) {
      setErrorImages(true);
      setTimeout(() => setErrorImages(false), 3000);
    } else {
      const files = event.target.files;
      setSelectedImages(files);
      console.log(selectedImages);
      const selectedFiles = event.target.files;
      const selectedFilesArray = Array.from(selectedFiles);
      const imagesArray = selectedFilesArray.map((file) => {
        return URL.createObjectURL(file);
      });
      setUrlsSelectedImages(imagesArray);
    }
  };

  const deleteHandler = (image) => {
    setUrlsSelectedImages(urlsSelectedImages.filter((e) => e !== image));
    URL.revokeObjectURL(image);
    // i need extract de url of selectedImages to compare con image
    console.log(image);
  };

  useEffect(() => {
    setImages(selectedImages);
    console.log(selectedImages);
    console.log(typeof selectedImages);
  }, [selectedImages]);

  return (
    <div className="loadImages__container">
      <div className="image-upload-wrap">
        <input
          className="file-upload-input"
          type="file"
          accept="image/*"
          multiple
          onChange={onSelectFile}
        />
        <div className="text-information">
          <img src={uploadImage} alt="upload images" />
          <h3>Agrega o arrastra tus imágenes aquí</h3>
        </div>
      </div>
      <div className="load-preview-images">
        {selectedImages.length > 0 && selectedImages.length < 11 ? (
          <PreviewImages images={urlsSelectedImages} deleteHandler={deleteHandler} />
        ) : null}
      </div>
      <div className={` error-images ${errorImages ? "error" : ""}`}>
        Sólo puedes subir un máximo de 10 imágenes
      </div>
    </div>
  );
};
