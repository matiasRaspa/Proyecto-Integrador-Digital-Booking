import React, { useContext, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FilterContext } from '../../../FilterContext';
import { getDateFormat } from '../../../helper/getDateFormat';
import { Card } from '../../card/Card';
import ModalPortal from '../../modals/ModalPortal';
import { ViewMap } from '../../modals/ViewMap';

export const Recommendation = ({ currentItems }) => {

    const [productId, setProductId] = useState(null);
    const [state, setState] = useState(false)
    const { valuesForm, setValuesForm, selectedCategory, setSelectedCategory } = useContext(FilterContext);
    const startDate = valuesForm.date.startDate === null ? '' : getDateFormat.getDate(valuesForm.date.startDate);
    const checkOut = valuesForm.date.endDate === null ? '' : getDateFormat.getDate(valuesForm.date.endDate);
    const date = startDate + ' / ' + checkOut;
    const message = 'No disponible'

    const handleClearFilters = () => {
        setSelectedCategory(null)
        setValuesForm({
            city: null,
            date: {
                startDate: null,
                endDate: null
            }
        }
        );
    }

    const handleDeleteCategory = () => {
        setSelectedCategory(null)
    }

    const handleDeleteCity = () => {
        setValuesForm(prevState => {
            return {
                ...prevState,
                city: null,
            };
        });
    }

    const handleDeleteDate = () => {
        setValuesForm(prevState => {
            return {
                ...prevState,
                date: {
                    startDate: null,
                    endDate: null
                }
            };
        });
    }

    return (
        <>
            <ModalPortal>
                <ViewMap state={state} setState={setState} productId={productId} />
            </ModalPortal>

            {
                selectedCategory !== null || valuesForm.city !== null || valuesForm.date.startDate !== null ?
                    <span className='body__delete' onClick={handleClearFilters}> Limpiar Filtros 🧹</span> : ''
            }

            <div className='body__recommendation'>
                <div>
                    <h2> Recomendaciones </h2>
                    <br />
                    <div className='body__filter-container'>
                        {selectedCategory === null ? '' :
                            <span className='body__filter'> {selectedCategory} <FontAwesomeIcon onClick={handleDeleteCategory} className='body__x' icon={faXmark} /> </span>
                        }
                        {valuesForm.city === null ? '' :
                            <span className='body__filter'> {valuesForm.city} <FontAwesomeIcon onClick={handleDeleteCity} className='body__x' icon={faXmark} /></span>
                        }
                        {valuesForm.date.startDate === null ? '' :
                            <span className='body__filter'> {date} <FontAwesomeIcon onClick={handleDeleteDate} className='body__x' icon={faXmark} /></span>
                        }
                    </div>

                    <br />
                </div>

                <section className='body__section info'>
                    {
                        typeof currentItems === 'string' ? 'No se encontraron resultados' :
                            currentItems && currentItems.map((product) => {
                                return (
                                    <div key={product.id} className='body__card recommendation respo'>
                                        <Card
                                            id={product.id !== null ? product.id : message}
                                            image={product.image !== null ? product.image.url : message}
                                            category={product.category !== null ? product.category : message}
                                            stars={product.stars !== null ? product.stars : message}
                                            name={product.name !== null ? product.name : message}
                                            score={product.score !== null ? product.score : message}
                                            features={product.features !== null ? product.features : message}
                                            description={product.description !== null ? product.description : message}
                                            setProductId={setProductId}
                                            setState={setState}
                                        />
                                    </div>

                                )

                            })
                    }

                </section>
            </div>
        </>
    )
}
