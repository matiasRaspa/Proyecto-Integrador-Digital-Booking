import React, { useContext, useEffect, useState } from 'react';
import ReactPaginate from 'react-paginate';
import { FilterContext } from '../../../FilterContext';
import { getDateFormat } from '../../../helper/getDateFormat';
import { useFetch } from '../../../hooks/useFetch';
import { Recommendation } from '../recommendation/Recommendation';


export const Paginate = ({ itemsPerPage }) => {

    const { valuesForm, selectedCategory } = useContext(FilterContext);

    const categoryName = selectedCategory === null ? '%20' : selectedCategory;
    const checkIn = valuesForm.date.startDate === null ? '%20' : getDateFormat.getFullDate(valuesForm.date.startDate);
    const checkOut = valuesForm.date.endDate === null ? '%20' : getDateFormat.getFullDate(valuesForm.date.endDate);
    const locationName = valuesForm.city === null ? '%20' : valuesForm.city;

    const [products] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/productView/home?categoryName=${categoryName}&startDate=${checkIn}&endDate=${checkOut}&locationName=${locationName}`);

    let items = products && products;

    const [currentItems, setCurrentItems] = useState(null);
    const [pageCount, setPageCount] = useState(0);
    const [itemOffset, setItemOffset] = useState(0);

    useEffect(() => {
        const endOffset = itemOffset + itemsPerPage;
        setCurrentItems(items && items.slice(itemOffset, endOffset));
        setPageCount(Math.ceil(items && items.length / itemsPerPage));

    }, [itemOffset, itemsPerPage, items]);

    const handlePageClick = (event) => {

        const newOffset = ((event.selected) * itemsPerPage)
        setItemOffset(newOffset);
    };

    return (
        <>
            <Recommendation currentItems={currentItems} />
            {
                typeof currentItems === 'string' ? '' :

                    <ReactPaginate
                        nextLabel=" >"
                        onPageChange={handlePageClick}
                        pageRangeDisplayed={1}
                        marginPagesDisplayed={2}
                        pageCount={pageCount}
                        previousLabel="< "
                        pageClassName="paginate__item"
                        previousClassName="paginate__item"
                        previousLinkClassName="paginate__link"
                        nextClassName="paginate__item arrow"
                        nextLinkClassName="paginate__link"
                        breakLabel="..."
                        breakClassName="paginate__item"
                        containerClassName="paginate__container "
                        activeClassName="paginate__active"
                        renderOnZeroPageCount={null}
                    />
            }
        </>
    );
}
