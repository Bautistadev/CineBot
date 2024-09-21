// "@/components/ui/input.jsx"
import React from "react";

export const Input = ({ className = "", ...props }) => {
  const baseStyles = "border border-gray-300 rounded-md px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500";

  return (
    <input
      className={`${baseStyles} ${className}`}
      {...props}
    />
  );
};
