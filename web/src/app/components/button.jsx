export const Button = ({ children, variant = "default", size = "md", ...props }) => {
  const baseStyles = "inline-flex items-center justify-center rounded-md font-medium focus:outline-none";
  const sizeStyles = {
    sm: "px-2 py-1 text-sm",
    md: "px-4 py-2 text-base",
    lg: "px-6 py-3 text-lg",
    icon: "p-2",
  };
  const variantStyles = {
    default: "bg-blue-600 text-white hover:bg-blue-700",
    outline: "border border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white",
  };

  const classes = `${baseStyles} ${sizeStyles[size]} ${variantStyles[variant]}`;

  return (
    <button className={classes} {...props}>
      {children}
    </button>
  );
};
