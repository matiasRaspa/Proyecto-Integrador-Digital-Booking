import React, { useContext } from 'react'
import { FilterContext } from '../../../FilterContext';
import { useFetch } from '../../../hooks/useFetch';

export const Category = () => {

    const { setSelectedCategory } = useContext(FilterContext);

    const [categories] = useFetch('http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/categories');


    const handleClick = (title) => {

        setSelectedCategory(title);
    }

    return (
        <div className='body__category'>
            <h2>Busqueda por alojamiento</h2>
            <br />
            <section className='body__section'>
                {
                    categories && categories.map((category) => {
                        return (
                            <div onClick={() => handleClick(category.title)} key={category.id} className='body__card category'>
                                <img className='body__img' src={category.imageUrl} alt="" />
                                <h4>{category.title}</h4>
                                <p>{category.description} </p>
                            </div>
                        )
                    })
                }
            </section>
        </div>
    )
}
