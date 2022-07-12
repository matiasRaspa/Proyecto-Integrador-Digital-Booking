export const getDescriptionScore = (num) => {
    let descriptionScore = 'Regular';
    if (num > 3 && num <= 6) {
        descriptionScore = 'Bueno'
    }
    if (num > 6 && num <= 10) {
        descriptionScore = 'Muy bueno'
    }
    return descriptionScore;
}
