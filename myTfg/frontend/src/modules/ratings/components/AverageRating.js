import React from "react";


const AverageRating = ({avgRating}) => {
    
    function roundToTwo(num) {
	  let rounded = Math.round((num + Number.EPSILON) * 100) / 100;
	  let str = rounded.toString();
	  if (str.indexOf('.00') > -1) {
	    str = str.substring(0, str.length - 3);
	  }
	  return str;
	}

    return(
        <div style={{transform:'translateY(10px)', color:'#212529'}}>
        	<span style={{transform:'translateY(-20px)', display:'inline-block', color:'#faaf00'}} data-testid="rating-value">{roundToTwo(avgRating)}</span>
            <span style={{transform:'translateY(-10px)', display:'inline-block'}}>/</span>
            <span>5</span>
        </div>
    )
}
export default AverageRating