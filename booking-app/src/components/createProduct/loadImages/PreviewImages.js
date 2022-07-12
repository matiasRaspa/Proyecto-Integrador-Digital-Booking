import React, { useState } from 'react';
import { DragDropContext, Droppable, Draggable } from "react-beautiful-dnd";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faRectangleXmark } from '@fortawesome/free-regular-svg-icons';

export const PreviewImages = ({ images, deleteHandler }) => {

  const [selectedImages, setSelectedImages] = useState(images);


  const reorder = (list, startIndex, endIndex) => {
    const result = [...list];
    const [removed] = result.splice(startIndex, 1);
    result.splice(endIndex, 0, removed);

    return result;
  };

  return (
    <DragDropContext
      onDragEnd={(result) => {
        const { source, destination } = result;
        if (!destination) {
          return;
        }
        if (
          source.index === destination.index &&
          source.droppableId === destination.droppableId
        ) {
          return;
        }

        setSelectedImages((prevImage) =>
          reorder(prevImage, source.index, destination.index)
        );
      }}
    >
      <div className='previewImages__container'>
        <Droppable droppableId="preview" direction='horizontal'>
          {(droppableProvided) => (
            <ul
              {...droppableProvided.droppableProps}
              ref={droppableProvided.innerRef}
              className="wrap-images"
            >
              {images.map((image, index) => (
                <Draggable key={image} draggableId={image} index={index}>
                  {(draggableProvided) => (
                    <li
                      {...draggableProvided.draggableProps}
                      ref={draggableProvided.innerRef}
                      {...draggableProvided.dragHandleProps}
                      className="image-item"
                    >
                
                        <img src={image} alt={`image ${index}`} />
                        <span onClick={() => deleteHandler(image)}>
                          <FontAwesomeIcon className='icon-close' icon={ faRectangleXmark } />
                        </span>
                        {/* <div className='bullet'>{index + 1}</div> */}
                    </li>
                  )}
                </Draggable>
              ))}
              {droppableProvided.placeholder}
            </ul>
          )}
        </Droppable>
      </div>
    </DragDropContext>
  )
}
